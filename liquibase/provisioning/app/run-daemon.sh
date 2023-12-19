#!/bin/bash

docker run -d -t -i -p 8081:8081 \
  --restart always --name playground playground:1.0