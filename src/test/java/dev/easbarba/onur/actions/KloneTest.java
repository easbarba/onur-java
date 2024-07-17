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

package dev.easbarba.onur.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KloneTest {
  private Config config;

  @BeforeEach
  void init() {
    this.config = ConfigProvider.getConfig();
  }

  @Test
  public void shouldHaveConfigDepth() {
    assertEquals(1, config.getValue("depth", Integer.class));
  }

  @Test
  public void shouldHaveConfigSingleBranch() {
    assertEquals(true, config.getValue("single-branch", Boolean.class));
  }

  @Test
  public void shouldHaveConfigQuiet() {
    assertEquals(true, config.getValue("quiet", Boolean.class));
  }
}
