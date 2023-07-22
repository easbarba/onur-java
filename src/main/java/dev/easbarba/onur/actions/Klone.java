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

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.microprofile.config.ConfigProvider;

public class Klone implements Runnable {
    private final String url;
    private final File root;
    private final String branch;

    public Klone(final String url, final File root, final String branch) {
        this.url = url;
        this.root = root;
        this.branch = branch;
    }

    @Override
    public void run() {
        var config = ConfigProvider.getConfig();

        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setBranch(branch)
                    .setDirectory(root)
                    .setDepth(config.getValue("depth", Integer.class))
                    .setCloneAllBranches(config.getValue("single-branch", Boolean.class))
                    .call()
                    .close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Pull [url=" + url + ", root=" + root + ", branch=" + branch + "]";
    }
}
