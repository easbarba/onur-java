/*
 *  Onur is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Onur is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Onur. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.easbarba.onur.database;

import dev.easbarba.onur.misc.Globals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public final class Repository {
  private final Path configHome;

  public Repository() {
    final var globals = Globals.getInstance();
    this.configHome = (Path) globals.get("config-home");
  }

  public Set<Path> namespath() {
    try {
      return base();
    } catch (final IOException e) {
      return null;
    }
  }

  private Set<Path> base() throws IOException {
    try (var files = Files.list(this.configHome)) {
      return files
          .filter(file -> file.toString().endsWith(".json")) // json files only
          .filter(file -> Files.exists(file)) // remove dangling symbolic links
          .filter(
              file -> { // remove empty files
                try {
                  return Files.size(file) != 0;
                } catch (final IOException ex) {
                  // ex.printStackTrace(); // to log
                  return false;
                }
              })
          .sorted()
          .collect(Collectors.toSet());
    } catch (final Exception ex) {
      throw ex;
    }
  }

  public long count() {
    try {
      return base().toArray().length;
    } catch (final IOException ex) {
      ex.printStackTrace();
      return 0;
    }
  }

  public Boolean exists() {
    return Files.exists((this.configHome));
  }

  public Path absolutePath() {
    return this.configHome.toAbsolutePath();
  }
}
