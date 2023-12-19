#!/bin/bash

./package.sh && ./shutdown.sh && ./build.sh && ./run-daemon.sh && ./logs.sh