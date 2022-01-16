#!/usr/bin/env bash

docker stop influxdb-ia

docker rm influxdb-ia

docker build . -t influxdb-ia-image

docker run --name influxdb-ia -d -p 8086:8086 influxdb-ia-image
