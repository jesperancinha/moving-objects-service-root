SHELL=/bin/bash

buildw:
	gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
buildw-prod:
	cd ../moving-objects-security-dsl && make buildw
	gradle -Pprod build
var-export:
	bash exportVars.sh
run-prod:
	java -jar build/libs/moving-objects-rest-service.jar
