#!/usr/bin/env bash

function replaceIp() {
    service=$1
    ip=$(getent hosts "$service" | awk '{ print $1 }')
    sed -i 's/'"$service"'/'"$ip"'/g' /etc/nginx/conf.d/default.conf
}

replaceIp moving-objects-rest-service
replaceIp moving-objects-jwt-service

cat /etc/nginx/conf.d/default.conf

pm2 start metrics-server/src/server.js
nginx
tail -f /dev/null