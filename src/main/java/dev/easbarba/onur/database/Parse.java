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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.easbarba.onur.domain.Configuration;
import dev.easbarba.onur.domain.Project;

public final class Parse {
    private Files files = new Files();

    public Parse() {
    }

    public Configuration one(File file) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Project> project = Arrays.asList(mapper.readValue(file, Project[].class));

            return new Configuration(file.getName(), project);
        } catch (Exception ex) {
            throw ex;
        }
    }

    // Parse all configuration files.
    public Map<String, Configuration> all() {
        Map<String, Configuration> configurations = new HashMap<String, Configuration>();

        files.namespath().forEach(file -> {
            try {
                configurations.put(file.getName(), one(file));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return configurations;
    }
}
