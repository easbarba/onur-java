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

# DEPENDENCIES: gawk, fzf, podman

NAME := onur
VERSION := $(shell gawk '/<version>/ { version=substr($$1,10,5); print version; exit }' pom.xml)
FULLNAME := ${USER}/${NAME}:${VERSION}

RUNNER ?= podman
MAVEN_CONTAINER := maven:3-eclipse-temurin-17
REPL_COMMAND := ${COMMAND}

cmds:
	${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${MAVEN_CONTAINER} bash -c './prepare.bash && ./mvnw $(shell cat maven_commands | fzf) --settings ./.mvn/settings.xml'

repl:
	${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${MAVEN_CONTAINER} bash

build:
	${RUNNER} build --file ./Containerfile --tag ${FULLNAME}

native:
	native-image -cp classes:./build/libs/javafinder-17.0.5-fat.jar --initialize-at-build-time=Constants -H:Name=javafinder eu.hansolo.javafinder.Main --no-fallback

.PHONY: test repl build cmds native
.DEFAULT_GOAL := test
