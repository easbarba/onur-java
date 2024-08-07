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

package dev.easbarba.onur.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "backup", description = "Backup projects.")
public final class Backup implements ICommands, Callable<Integer> {
  public Backup() {}

  @Option(
      names = {"-v", "--verbose"},
      description = "Provides more information.")
  private boolean verbose;

  @Override
  public Integer call() {
    run(verbose);

    return 0;
  }

  @Override
  public void run(Boolean verbose) {
    System.out.println("Backing up");
  }
}
