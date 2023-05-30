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

package dev.easbarba.onur.misc;

import java.nio.file.Path;

/*
 * Provides general project information.
 */
public final class Globals {
    private String homeDir = System.getProperty("user.home");
    private Path configHome = java.nio.file.Paths.get(this.homeDir,
            ".config", "onur");

    public String getHomeDir() {
        return this.homeDir;
    }

    public Path getConfigHome() {
        return this.configHome;
    }
}
