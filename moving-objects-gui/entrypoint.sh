#!/usr/bin/env bash

#IP replacement has been removed because with fixed IP's docker-compose may disrupt local network
#If you are running an old p project and you run into network issues, please update your version, remove all docker networks and start again.
#function replaceIp() {
#    service=$1
#    ip=$(getent hosts "$service" | awk '{ print $1 }')
#    sed -i 's/'"$service"'/'"$ip"'/g' /etc/nginx/conf.d/default.conf
#}
#
#replaceIp moving-objects-rest-service
#replaceIp moving-objects-jwt-service

cat /etc/nginx/conf.d/default.conf

/usr/local/bin/pm2 start metrics-server/src/server.js
nginx
tail -f /dev/null