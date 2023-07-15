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

import java.io.File;
import java.nio.file.Paths;
import org.eclipse.jgit.api.Git;
import dev.easbarba.onur.database.Parse;
import dev.easbarba.onur.misc.Globals;

public class Grab implements ICommands {
    private void klone(String url, File root, String branch) {
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setBranch(branch)
                    .setDirectory(root)
                    .setDepth(1)
                    .setCloneAllBranches(false)
                    .call()
                    .close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void pull(File folder, String branch) {
        try (var git = Git.open(folder)) {
            git.pull()
                    .setRemote("origin")
                    .setRemoteBranchName(branch)
                    .call();

            git.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Globals globals = Globals.getInstance();
            var parse = new Parse();

            parse.all().forEach(cfg -> {
                System.out.println(new StringBuilder("\n").append(cfg.topic()).append(": \n"));

                cfg.projects().forEach(pj -> {
                    var root = Paths.get(globals.get("projects-home").toString(), cfg.topic(), pj.getName()).toFile();

                    System.out.println(pj.getName());

                    if (root.exists()) {
                        pull(root, pj.getBranch());
                    } else {
                        klone(pj.getUrl(), root, pj.getBranch());
                    }
                });
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
