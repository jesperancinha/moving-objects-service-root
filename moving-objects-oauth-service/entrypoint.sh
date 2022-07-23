#!/usr/bin/env bash
java -jar -Dpostgres.host="${MOS_DB_IP}" moving-objects-oauth-service.jar --spring.profiles.active=docker
