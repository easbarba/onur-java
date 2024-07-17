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

public final class RepositoryTest {
  private Repository repository;

  @BeforeEach
  void init() {
    this.repository = new Repository();
  }

  @Test
  public void shoudl_have_correct_configurations_count() {
    assertEquals(this.repository.count(), 4);
  }

  @Test
  public void shouldHaveASpecificConfiguration() {
    assertEquals(
        this.repository.namespath().stream().findFirst().get().getFileName().toString(),
        "lualink.json");
  }

  @Test
  public void configuration_should_exist() {
    assertTrue(this.repository.exists());
  }
}
