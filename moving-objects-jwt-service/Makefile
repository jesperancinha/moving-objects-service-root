buildw:
	cd .. && make generateCredentials.sh
	gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
get-token:
	curl -i -XPOST user:password@localhost:8081/token
