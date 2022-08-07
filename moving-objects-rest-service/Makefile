SHELL=/bin/bash

buildw:
	gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
buildw-prod:
	gradle -Pprod build
	bash exportVars.sh
run-prod:
	java -jar build/libs/moving-objects-rest-service.jar
