#!/bin/bash

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose logs "$name" > "logs"
    string=$(cat logs)
    counter=0
    while [[ "$string" != *"$message"* ]]
    do
      printf "."
      docker-compose logs "$name" > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo "Failed after $counter tries! Cypress tests may fail!!"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo "Succeeded $name Service after $counter tries!"
}

checkServiceByNameAndMessage mosdb 'database system is ready to accept connections'
checkServiceByNameAndMessage moving-objects-jwt-service 'Netty started on port'
checkServiceByNameAndMessage moving-objects-rest-service 'Netty started on port'
checkServiceByNameAndMessage grafana 'HTTP Server Listen" address=[::]:3000'
checkServiceByNameAndMessage prometheus '"Start listening for connections" address=0.0.0.0:9090'
checkServiceByNameAndMessage influxdb 'service=tcp-listener transport=http addr=:8086 port=8086'
checkServiceByNameAndMessage nginx '[PM2] Done.'
