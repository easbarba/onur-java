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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class GlobalsTest {
    private Globals globals;

    @BeforeEach
    void init() {
        this.globals = Globals.getInstance();
    }

    @Test
    public void shouldHaveValidFilesExactly() {
        assertEquals(Paths.get(System.getProperty("user.home").toString(), ".config", "onur").toString(),
                globals.get("config-home"));
    }
}
