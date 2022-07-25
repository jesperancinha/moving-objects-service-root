#!/bin/bash
cd /opt/moving-objects-gui && yarn && npm run build-docker && cd ../e2e && yarn