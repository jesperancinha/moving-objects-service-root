#!/usr/bin/env bash

docker-machine start dev

docker-machine env dev

eval $(docker-machine env dev)

docker-compose down

docker stop app-ia

docker rm app-ia

gradle clean build install test

cd international-airports-gui
yarn install --force
yarn build
cd ..

docker-compose up -d --build --remove-orphans
