#!/usr/bin/env bash

docker stop app-ia

docker rm app-ia

gradle clean build install test

cd moving-objects-gui || exit
yarn install --force
yarn build
cd ..

echo "Building with --build-arg AIRPORTS_KEY=$1 --build-arg WEBCAMS_KEY=$2 -t app-ia-image"

docker build . --build-arg AIRPORTS_KEY="$1" --build-arg WEBCAMS_KEY="$2" -t app-ia-image

echo "Let's just open 8080 and 8082 and 4000. This way we can see that the single source of truth service is running, but not accessible from the outside."
docker run --name app-ia -d -p 8080:80 -p 8082:8082 -p 4000:4000 app-ia-image
