# https://hub.docker.com/_/openjdk/
# MAVEN Build Stage
FROM maven:3.8.3-openjdk-17-slim as maven
LABEL maintainer="ahamza"

# Copy project source
COPY . /app
WORKDIR /app

# Building Project Artifact 
RUN mvn clean install

# JAVA Build Stage
FROM openjdk:17.0.2-jdk-slim
LABEL maintainer="ahamza"

# Copy java artifact from previous stage
COPY --from=maven --chown=user:user /app/plant-library/target/plant-library.jar /app/plant-library.jar

WORKDIR /app

USER user

SHELL ["/bin/bash", "-c"]

ENTRYPOINT echo java -jar "-Dspring.datasource.url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME" \
                        "-Dspring.datasource.username=$DB_USERNAME" \
                        "-Dspring.datasource.password=$DB_PASSWORD" \
                        "-Dspring.profiles.active=$PROFILE" \
                        /app/playground.jar
