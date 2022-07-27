#!/usr/bin/env bash
java -jar -Dpostgres.host="${MOS_DB_IP}" moving-objects-jwt-service.jar --spring.profiles.active=docker
