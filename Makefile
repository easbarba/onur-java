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

-include .env

RUNNER ?= podman
NAME := onur
VERSION := $(shell gawk '/<version>/ { version=substr($$1,10,5); print version; exit }' pom.xml)

command:
	@${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${OPENJDK_IMAGE} \
		bash -c './prepare.bash && ./mvnw --settings ./.mvn/settings.xml $(shell cat commands | fzf)'

test:
	@${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${OPENJDK_IMAGE} \
		bash -c './prepare.bash && ./mvnw --settings clean test'

repl:
	@${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${OPENJDK_IMAGE} bash

build:
	${RUNNER} build --file ./Containerfile --tag ${USER}/${NAME}:${VERSION}

.PHONY: test repl build command native
.DEFAULT_GOAL := test
