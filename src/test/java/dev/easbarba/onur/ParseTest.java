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

package dev.easbarba.onur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dev.easbarba.onur.database.Parse;
import dev.easbarba.onur.domain.Configuration;

public final class ParseTest {
    private Parse parse;

    @BeforeEach
    void init() {
        this.parse = new Parse();
    }

    @Test
    public void shouldHaveCorrectConfigurationsSize() {
        assertEquals(2, this.parse.all().size());
    }

    @Test
    public void shouldHaveMiscCorrectProjectsSize() {
        var fi = Paths.get(System.getProperty("user.home"), ".config", "onur", "misc.json").toFile();
        try {
            var config = parse.one(fi).projects();
            assertEquals(3, config.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
