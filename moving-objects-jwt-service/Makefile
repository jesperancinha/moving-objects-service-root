include ../Makefile.mk

SHELL=/bin/bash

buildw:
	cd .. && make generateCredentials.sh
	gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
get-token-local:
	curl -i -XPOST admin:admin@localhost:8080/objects/token
get-token:
	curl -i -XPOST admin:admin@localhost:8081/objects/token
request-all-no-jwt:
	curl admin:admin@localhost:8081/objects/webcams/all && echo
request-all:
	curl -H 'Authorization: Bearer ${TOKEN}' localhost:8081/objects/webcams/all && echo
request-all-auto:
	curl -H 'Authorization: Bearer $(shell curl -XPOST admin:admin@localhost:8081/objects/token)' localhost:8081/objects/webcams/all && echo
