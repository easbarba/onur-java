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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class ConfigurationsTest {
    private Configurations configurations;

    @BeforeEach
    void init() {
        this.configurations = new Configurations();
    }

    @Test
    public void shouldHaveCorrectConfigurationsSize() {
        assertEquals(this.configurations.count(), 2);
    }

    @Test
    public void shouldHaveASpecificConfiguration() {
        assertEquals(this.configurations.namespath().stream().findFirst().get().getFileName().toString(),
                "etc.json");
    }

    @Test
    public void shouldConfigurationExist() {
        assertTrue(this.configurations.exists());
    }
}
