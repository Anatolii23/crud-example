#!/bin/bash

docker run -d -t -i \
--network postgresql \
-e DB_HOST=postgres-plant-library \
-e DB_PORT=5432 \
-e DB_NAME=plant-library \
-e DB_USERNAME=chnageme \
-e DB_PASSWORD=changeme \
-e DB_SCHEMA=plant \
--name plant-library-liquibase plant-library-liquibase:1.0
