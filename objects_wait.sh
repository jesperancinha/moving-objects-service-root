#!/bin/bash
GITHUB_RUN_ID=${GITHUB_RUN_ID:-123}

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose -p "${GITHUB_RUN_ID}" logs "$name" > "logs"
    string=$(cat logs)
    counter=0
    echo "Project $GITHUB_RUN_ID"
    echo -n "Starting service $name "
    while [[ "$string" != *"$message"* ]]
    do
      echo -e -n "\e[93m-\e[39m"
      docker-compose -p "${GITHUB_RUN_ID}" logs "$name" > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo -e "\e[91mFailed after $counter tries! Cypress tests may fail!!\e[39m"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo -e "\e[92m Succeeded starting $name Service after $counter tries!\e[39m"
}

checkServiceByNameAndMessage mosdb 'database system is ready to accept connections'
checkServiceByNameAndMessage moving-objects-jwt-service 'Netty started on port'
checkServiceByNameAndMessage moving-objects-rest-service 'Netty started on port'
checkServiceByNameAndMessage grafana 'HTTP Server Listen" address=[::]:3000'
checkServiceByNameAndMessage prometheus '"Start listening for connections" address=0.0.0.0:9090'
checkServiceByNameAndMessage influxdb 'service=tcp-listener transport=http addr=:8086 port=8086'
checkServiceByNameAndMessage nginx '[PM2] Done.'
