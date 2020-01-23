#!/usr/bin/env bash

gradle build install -p international-airports-sst/international-airports-sst-client/international-airports-sst-client-webcam
gradle build install -p international-airports-sst/international-airports-sst-client/international-airports-sst-client-airports
gradle build install -p international-airports-sst/international-airports-sst-data
gradle build install -p international-airports-sst/international-airports-sst-mock
gradle build install -p international-airports-sst/international-airports-sst-live
gradle build install -p international-airports-rest/international-airports-data
gradle build install -p international-airports-rest/international-airports-model
gradle build install -p international-airports-rest/international-airports-service-api
gradle build install -p international-airports-rest/international-airports-rest-api
gradle build install -p international-airports-rest/international-airports-rest-service

cd international-airports-gui
npm clean-install
npm run build
cd ..

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker stop app-ia

docker rm app-ia

docker-compose up -d --build --remove-orphans
