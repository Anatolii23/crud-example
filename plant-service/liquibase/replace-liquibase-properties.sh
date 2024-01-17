#!/bin/sh

sed -i "s|<host>|${DB_HOST}|g" /liquibase/liquibase.properties
sed -i "s|<port>|${DB_PORT}|g" /liquibase/liquibase.properties
sed -i "s|<db_name>|${DB_NAME}|g" /liquibase/liquibase.properties
sed -i "s|<username>|${DB_USERNAME}|g" /liquibase/liquibase.properties
sed -i "s|<password>|${DB_PASSWORD}|g" /liquibase/liquibase.properties
sed -i "s|<schema_name>|${DB_SCHEMA}|g" /liquibase/liquibase.properties

# Pass execution to Main container process (set by RUN directive)
exec "$@"
