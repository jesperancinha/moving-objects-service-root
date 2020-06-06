#!/usr/bin/env bash

docker-compose down

docker stop app-ia

docker rm app-ia

gradle clean build install test

cd international-airports-gui
yarn install --force
yarn build
cd ..

docker-compose up -d --build --remove-orphans
