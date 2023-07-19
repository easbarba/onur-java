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

import java.nio.file.Paths;

import dev.easbarba.onur.actions.Klone;
import dev.easbarba.onur.actions.Pull;
import dev.easbarba.onur.database.Parse;
import dev.easbarba.onur.misc.Globals;

public class Grab implements ICommands {
    @Override
    public void run() throws InterruptedException {
        final Globals globals = Globals.getInstance();
        final var parse = new Parse();

        parse.all().forEach(cfg -> {
            System.out.println(new StringBuilder("\n").append(cfg.topic()).append(": \n"));

            cfg.projects().forEach(pj -> {
                final var root = Paths.get(globals.get("projects-home").toString(), cfg.topic(), pj.getName()).toFile();

                System.out.println(pj.getName());

                if (root.exists()) {
                    final var p = new Thread(new Pull(root, pj.getBranch()));
                    p.start();

                    try {
                        p.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    final var k = new Thread(new Klone(pj.getUrl(), root, pj.getBranch()));
                    k.start();

                    try {
                        k.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
