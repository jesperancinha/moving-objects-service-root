#!/usr/bin/env bash
java -jar international-airports-sst-live-1.0.0.jar --rapidapi.airports.key="$1" --rapidapi.webcams.key="$2" &
java -jar international-airports-rest-service-1.0.0.jar
