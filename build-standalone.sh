#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker stop app-ia

docker rm app-ia

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

echo "Building with --build-arg AIRPORTS_KEY=$1 --build-arg WEBCAMS_KEY=$2 -t app-ia-image"

docker build . --build-arg AIRPORTS_KEY=$1 --build-arg WEBCAMS_KEY=$2 -t app-ia-image

echo "Let's just open 8080 and 8082. This way we can see that the single source of truth service is running, but not accessible from the outside."
docker run --name app-ia -d -p 8080:80 -p 8082:8082 app-ia-image
