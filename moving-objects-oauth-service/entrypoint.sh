#!/usr/bin/env bash
java -Dpostgres.host=${MOS_DB_IP} -jar moving-objects-oauth-service.jar --spring.profiles.active=docker
