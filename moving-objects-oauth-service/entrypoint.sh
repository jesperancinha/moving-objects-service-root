#!/usr/bin/env bash
java -jar -Dpostgres.host=${MOS_DB_IP} --spring.profiles.active=docker moving-objects-oauth-service.jar
