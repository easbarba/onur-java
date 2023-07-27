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

import dev.easbarba.onur.actions.Klone;
import dev.easbarba.onur.actions.Pull;
import dev.easbarba.onur.database.Parse;
import dev.easbarba.onur.domain.Project;
import dev.easbarba.onur.misc.Globals;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Paths;
import java.util.concurrent.Callable;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

@Command(name = "grab", description = "Grab projects.")
public class Grab implements ICommands, Callable<Integer> {
  // private static final Logger LOGGER = LogManager.getLogger(Grab.class.getName());

  @Option(names = { "-v", "--verbose" }, description = "Provides more information.")
  private boolean verbose;

  @Override
  public void run(Boolean verbose) {
    final Globals globals = Globals.getInstance();
    final var parse = new Parse();

    parse.all().forEach(configuration -> {
      System.out.println();
      System.out.println(new StringBuilder(configuration.topic().toUpperCase())
          .toString());

      configuration.projects().forEach(project -> {
        final var root = Paths
            .get(globals.get("projects-home").toString(),
                configuration.topic(), project.name())
            .toFile();

        final var message = new StringBuilder().append(project.name());
        if (verbose) {
          message.append(" - ").append(project.branch());
        }

        printinfo(project);
        if (root.exists()) {
          new Pull(root, project.branch()).run();
        } else {
          new Klone(project.url(), root, project.branch()).run();
        }
      });
    });
  }

  @Override
  public Integer call() {
    run(verbose);
    return 0;
  }

  public void printinfo(Project project) {
    System.out.printf("%-4s    %-35s   %-70s   %s\n", "", project.name(), project.url(), project.branch());
  }
}
