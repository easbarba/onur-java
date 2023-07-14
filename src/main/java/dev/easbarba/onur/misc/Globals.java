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

import java.util.HashMap;
import java.util.Map;

/*
 * Provides general project information.
 */
public final class Globals {
    private static Globals instance = null;
    private Map<String, String> proprieties = new HashMap<String, String>();

    public Globals() {
        defaultProperties();
    }

    public static Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }

        return instance;
    }

    private void defaultProperties() {
        var homeDir = System.getProperty("user.home");

        set("home", homeDir);
        set("config-home", java.nio.file.Paths.get(homeDir, ".config", "onur").toString());
        set("projects-home", java.nio.file.Paths.get(homeDir, "Projects").toString());
    }

    public String get(String key) {
        return proprieties.get(key);
    }

    public void set(String key, String value) {
        this.proprieties.put(key, value);
    }
}
