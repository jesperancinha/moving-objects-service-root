#!/bin/bash
sudo chown 1000:1000 /opt/moving-objects-gui
cd /opt/moving-objects-gui && yarn && npm run build-docker
