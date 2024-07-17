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
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "grab", description = "Grab projects.")
public class Grab implements ICommands, Callable<Integer> {
  private static final Logger LOGGER = LogManager.getLogger(Grab.class.getName());

  @Option(
      names = {"-v", "--verbose"},
      description = "Provides more information.")
  private boolean verbose;

  @Override
  public void run(Boolean verbose) {
    final Globals globals = Globals.getInstance();
    final var parse = new Parse();

    parse
        .multi()
        .forEach(
            configuration -> {
              System.out.println();
              System.out.println(new StringBuilder(configuration.name()).toString());

              configuration
                  .topics()
                  .forEach(
                      (topicName, projects) -> {
                        System.out.printf("%-2s%s\n", " ", topicName);

                        projects.forEach(
                            project -> {
                              final var absPath =
                                  Paths.get(
                                          globals.get("projects-home").toString(),
                                          configuration.name(),
                                          topicName,
                                          project.name())
                                      .toFile();

                              final var message = new StringBuilder().append(project.name());
                              if (verbose) {
                                message.append(" - ").append(project.branch());
                              }

                              printinfo(project);
                              if (absPath.exists()) {
                                new Pull(absPath, project.branch()).run();
                              } else {
                                new Klone(project.url(), absPath, project.branch()).run();
                              }
                            });

                        System.out.println();
                      });
            });
  }

  @Override
  public Integer call() {
    run(verbose);
    return 0;
  }

  public void printinfo(Project project) {
    var nameTruncated =
        Optional.ofNullable(project.name())
            .filter(s -> s.length() >= 30)
            .map(s -> s.substring(0, 30).concat("..."))
            .orElseGet(() -> project.name());
    var urlTruncated =
        Optional.ofNullable(project.url())
            .filter(s -> s.length() >= 60)
            .map(s -> s.substring(0, 60).concat("..."))
            .orElseGet(() -> project.url());
    var message =
        String.format("%-4s%-40s%-70s%s\n", " ", nameTruncated, urlTruncated, project.branch());

    System.out.printf(message);
    LOGGER.info(message);
  }
}
