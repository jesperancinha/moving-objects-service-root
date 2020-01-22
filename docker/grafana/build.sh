#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker stop grafana-ia

docker rm grafana-ia

docker build . -t grafana-ia-image

docker run --name grafana-ia -d -p 3000:3000 grafana-ia-image
