#!/bin/bash

cd "$(dirname "$0")/changelog"
tar -zcvf changesets.tar.gz .
mv -f changesets.tar.gz ..