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

# DEPENDENCIES: gawk, fzf, podman, java 17, Oracle GraalVM 22.0.2+9.1

.DEFAULT_GOAL := test

RUNNER ?= podman
NAME := onur
VERSION := $(shell gawk '/<version>/ { version=substr($$1,10,5); print version; exit }' pom.xml)
CONTAINER_IMAGE := registry.gitlab.com/easbarba/onur-java:${VERSION}

.PHONY: commands
command:
	@${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${CONTAINER_IMAGE} \
		bash -c './mvnw --settings ./.mvn/settings.xml clean compile -P development $(shell cat container-commands | fzf)'

.PHONY: test
test:
	@${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${CONTAINER_IMAGE} \
		bash -c './mvnw --settings ./.mvn/settings.xml clean compile test'

.PHONY: image.build
image.build:
	${RUNNER} build --file ./Containerfile --tag ${CONTAINER_IMAGE}

.PHONY: image.repl
image.repl:
	${RUNNER} run --rm -it \
		--volume ${PWD}:/app:Z \
		--workdir /app \
		${CONTAINER_IMAGE} bash

.PHONY: image.publish
image.publish:
	${RUNNER} push ${CONTAINER_IMAGE}

.PHONY: install
install:
	# ./gradlew clean shadowJar -x test
	./mvnw clean package -D skipTests assembly:single
	native-image --no-fallback -jar target/onur-${VERSION}-jar-with-dependencies.jar -o ${HOME}/.local/bin/onur

.PHONY: fmt
fmt:
	google-java-format -r ./src/main/java/dev/easbarba/onur/*/***
	google-java-format -r ./src/test/java/dev/easbarba/onur/*/***

.PHONY: system
system:
	guix shell --pure --container
