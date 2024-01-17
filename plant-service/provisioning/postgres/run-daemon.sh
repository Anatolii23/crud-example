#!/bin/bash

docker network create postgresql || true
docker run \
    --network postgresql \
    -e POSTGRES_USER=changeme \
    -e POSTGRES_PASSWORD=changeme \
    -e POSTGRES_DB=plant-library \
    -e POSTGRES_SCHEMA=plant \
    -p 5432:5432 \
    -v ~/docker_volumes/postgres-data:/var/lib/postgresql/data \
    -d -t -i --name postgres postgres