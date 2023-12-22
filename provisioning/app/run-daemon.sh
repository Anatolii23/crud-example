#!/bin/bash

docker run -d -t -i -p 8081:8081 \
  --restart always --name plant-library plant-library:1.0