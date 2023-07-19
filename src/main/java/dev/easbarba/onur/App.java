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
import picocli.CommandLine.Option;

@Command(name = "onur", mixinStandardHelpOptions = true, version = "0.1.0", description = "Easily manage multiple FLOSS repositories.")
public final class App implements Callable<Integer> {
  @Option(names = { "-v", "--verbose" }, description = "Provides more information.")
  private boolean verbose;

  @Command(description = "Grab projects.")
  void grab() {
    try {
      new Grab().run();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Command(description = "Backup projects.")
  void backup() {
    new Backup().run();
  }

  @Override
  public Integer call() throws Exception {
    System.out.printf("Welcome");
    return 0;
  }

  public static void main(final String... args) {
    final int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
