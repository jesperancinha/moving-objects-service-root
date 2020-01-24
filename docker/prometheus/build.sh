#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker stop prometheus-ia

docker rm prometheus-ia

docker build . --build-arg PROMETHEUS_FILE=prometheus-standalone.yml -t prometheus-ia-image

docker run --name prometheus-ia -d -p 9090:9090 prometheus-ia-image
