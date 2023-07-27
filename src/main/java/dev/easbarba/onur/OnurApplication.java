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

import dev.easbarba.onur.commands.Backup;
import dev.easbarba.onur.commands.Grab;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "onur",
         mixinStandardHelpOptions = true,
         version = "0.3.0",
         description = "Easily manage multiple FLOSS repositories.",
         subcommands = {  Grab.class, Backup.class })
public final class OnurApplication implements Callable<Integer> {
  @Override
  public Integer call() throws Exception {
    System.out.printf("Onur");
    return 0;
  }

  public static void main(final String... args) {
    final int exitCode = new CommandLine(new OnurApplication()).execute(args);
    System.exit(exitCode);
  }
}
