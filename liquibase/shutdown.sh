#!/bin/bash

docker rm -v plant-library-liquibase || true
docker rmi plant-library-liquibase:1.0 || true
