#!/usr/bin/env sh
java -jar -Dpostgres.host="${MOS_DB_IP}" moving-objects-jwt-service.jar --spring.profiles.active=docker
