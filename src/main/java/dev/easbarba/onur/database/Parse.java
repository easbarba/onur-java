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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.easbarba.onur.domain.Configuration;
import dev.easbarba.onur.domain.Project;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

public final class Parse {
  private final Repository files = new Repository();

  public Parse() {}

  // Parse one configuration file
  public Configuration single(final Path file) throws Exception {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final HashMap<String, List<Project>> topics =
          mapper.readValue(file.toFile(), new TypeReference<HashMap<String, List<Project>>>() {});
      final var name = FilenameUtils.removeExtension(file.getFileName().toString());

      return new Configuration(name, topics);
    } catch (final Exception ex) {
      throw ex;
    }
  }

  // Bundle all configuration files.
  public List<Configuration> multi() {
    System.out.print("Configs: [");
    final List<Configuration> configurations = new ArrayList<Configuration>();

    files
        .namespath()
        .forEach(
            file -> {
              try {
                System.out.printf(" %s ", file);
                configurations.add(single(file));
              } catch (final Exception ex) {
                ex.printStackTrace();
              }
            });

    System.out.println("]");
    return configurations;
  }
}
