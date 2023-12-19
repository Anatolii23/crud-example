#!/bin/bash

docker run \
    --network postgresql \
    -e POSTGRES_USER=playground-admin \
    -e POSTGRES_PASSWORD=changeme \
    -e POSTGRES_DB=playground \
    -e POSTGRES_SCHEMA=playground \
    -p 5432:5432 \
    -v ~/docker_volumes/postgis-data:/var/lib/postgresql/data \
    -d -t -i --name postgis postgis/postgis