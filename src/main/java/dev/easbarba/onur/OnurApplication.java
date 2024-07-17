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

import static picocli.CommandLine.Command;

import dev.easbarba.onur.commands.Backup;
import dev.easbarba.onur.commands.Grab;
import picocli.CommandLine;

@Command(
    name = "onur",
    mixinStandardHelpOptions = true,
    version = "0.4.0",
    description = "Easily manage multiple FLOSS repositories.",
    subcommands = {Grab.class, Backup.class})
public final class OnurApplication {
  public static void main(final String... args) {
    final int exitCode = new CommandLine(new OnurApplication()).execute(args);
    System.exit(exitCode);
  }
}
