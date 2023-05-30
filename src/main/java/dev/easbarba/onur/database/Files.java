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

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.easbarba.onur.misc.Globals;

public final class Files {
    private File configHome;

    public Files() {
        this.configHome = new Globals().getConfigHome().toFile();
    }

    public Set<String> names() {
        return Stream.of(this.configHome.listFiles())
                .map(File::getName)
                .filter(file -> file.endsWith(".json"))
                .sorted()
                .collect(Collectors.toSet());
    }

    public long count() {
        return this.configHome.listFiles().length;
    }

    public Boolean exists() {
        return this.configHome.exists();
    }

    public String absolutePath() {
        return this.configHome.getAbsolutePath();
    }

}
