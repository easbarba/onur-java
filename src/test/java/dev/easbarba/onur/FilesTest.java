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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.easbarba.onur.database.Files;

public class FilesTest {
    private Files files;

    @BeforeEach
    void init() {
        this.files = new Files();
    }

    @Test
    public void shouldCountExactly() {
        assertEquals(this.files.count(), 5);
    }

    @Test
    public void shouldHaveMiscConfiguration() {
        assertEquals(this.files.names().stream().findFirst().get(), "etc.json");
    }

    @Test
    public void shouldExist() {
        assertTrue(this.files.exists());
    }
}
