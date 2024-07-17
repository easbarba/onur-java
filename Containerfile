# Onur is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# Onur is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with Onur. If not, see <https://www.gnu.org/licenses/>.

FROM docker.io/library/eclipse-temurin:21-jdk

MAINTAINER EAS Barbosa <easbarba@outlook.com>
LABEL version="4.0"
LABEL description="Easily manage multiple FLOSS repositories."

RUN apt-get update && \
        apt-get install -y --no-install-recommends git && \
        apt-get clean && \
        rm -rf /var/lib/apt/lists/*

ENV USERNAME easbarba
ENV APP_HOME /home/$USERNAME/app
WORKDIR $APP_HOME

RUN groupadd -r $USERNAME && useradd -r -g $USERNAME -d /home/$USERNAME -m -s /bin/bash $USERNAME
RUN chown -R $USERNAME:$USERNAME /home/$USERNAME

COPY pom.xml mvnw prepare.bash .
COPY .mvn/ .mvn
COPY examples/ examples
RUN ./prepare.bash
RUN ./mvnw verify #-D skipTests

COPY . .

CMD [ "./mvnw", "clean", "test" ]
