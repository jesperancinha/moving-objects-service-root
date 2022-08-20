buildw:
	cd .. && make generateCredentials.sh
	gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
get-token-local:
	curl -i -XPOST admin:admin@localhost:8080/objects/token
get-token:
	curl -i -XPOST admin:admin@localhost:8081/objects/token
request-all:
	curl -H 'Authorization: Bearer ${TOKEN}' localhost:8081/objects/webcams/all && echo
