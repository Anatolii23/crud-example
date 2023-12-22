FROM maven:3.8.3-openjdk-17-slim

LABEL maintainer="ahamza"

# Create app directory
RUN mkdir -p /liquibase
WORKDIR /liquibase

COPY changelog /liquibase
COPY pom.xml /liquibase/pom.xml
COPY liquibase.properties /liquibase/liquibase.properties

# Add entrypoint script to replace properties to access database
ADD replace-liquibase-properties.sh /liquibase/replace-liquibase-properties.sh
RUN chmod +x /liquibase/replace-liquibase-properties.sh

ENTRYPOINT ["/liquibase/replace-liquibase-properties.sh"]
CMD ["mvn", "liquibase:update", "-N"]
