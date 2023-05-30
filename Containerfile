FROM maven:eclipse-temurin as builder
MAINTAINER EAS Barbosa <easbarba@outlook.com>
RUN apt-get update && apt-get install -y git
WORKDIR /app
COPY pom.xml ./
RUN ./mvwn verify
RUN ./prepare.bash
COPY . .
CMD [""]