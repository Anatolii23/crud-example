#!/bin/bash

cp .dockerignore ../..
docker build -t plant-library:1.0 -f Dockerfile ../..
rm ../../.dockerignore
