#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker stop influxdb-ia

docker rm influxdb-ia

docker build . -t influxdb-ia-image

docker run --name influxdb-ia -d -p 8086:8086 influxdb-ia-image
