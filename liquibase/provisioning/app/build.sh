#!/bin/bash

cp .dockerignore ../..
docker build -t playground:1.0 -f Dockerfile ../..
rm ../../.dockerignore
