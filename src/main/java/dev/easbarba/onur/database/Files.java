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
import java.io.File;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Files {
    private File configHome;

    public Files() {
        var globals = Globals.getInstance();
        this.configHome = new File(globals.get("config-home"));
    }

    public Set<String> names() {
        return base()
                .sorted()
                .collect(Collectors.toSet());
    }

    public Set<File> namespath() {
        return base()
                .map(file -> Paths.get(configHome.toString(), file).toFile())
                .sorted()
                .collect(Collectors.toSet());
    }

    private Stream<String> base() {
        return Stream.of(this.configHome.listFiles())
                .map(File::getName)
                .filter(file -> file.endsWith(".json"))
                .filter(file -> valid(file));
    }

    // Check if file has all needed atttributes.
    private boolean valid(String file) {
        var currentFile = Paths.get(configHome.toString(), file).toFile();

        if (!currentFile.exists()) {
            return false;
        }

        if (currentFile.length() == 0) {
            return false;
        }

        return true;
    }

    public long count() {
        return base().collect(Collectors.toSet()).toArray().length;
    }

    public Boolean exists() {
        return this.configHome.exists();
    }

    public String absolutePath() {
        return this.configHome.getAbsolutePath();
    }
}
