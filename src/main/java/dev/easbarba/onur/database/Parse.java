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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import dev.easbarba.onur.domain.Configuration;
import dev.easbarba.onur.domain.Project;

public final class Parse {
    private Configurations files = new Configurations();

    public Parse() {
    }

    // Parse one configuration file
    public Configuration one(Path file) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Project> project = Arrays.asList(mapper.readValue(file.toFile(), Project[].class));
            var name = FilenameUtils.removeExtension(file.getFileName().toString());

            return new Configuration(name, project);
        } catch (Exception ex) {
            throw ex;
        }
    }

    // Bundle all configuration files.
    public List<Configuration> all() {
        List<Configuration> configurations = new ArrayList<Configuration>();

        files.namespath().forEach(file -> {
            try {
                configurations.add(one(file));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return configurations;
    }
}
