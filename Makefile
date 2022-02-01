wrapper:
	gradle wrapper
build-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd international-airports-rest/international-airports-model && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-rest/international-airports-data && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-rest/international-airports-rest-api && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-rest/international-airports-service-api && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-sst/international-airports-sst-data && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-common && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-airports && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd international-airports-sst/international-airports-sst-client/international-airports-sst-client-webcam && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	gradle clean build
	gradle test jacocoTestReport publishToMavenLocal
upgrade:
	gradle wrapper --gradle-version 7.4
upgrade-mac-os:
	brew upgrade gradle
	sdk install gradle
stop:
	docker-compose down --remove-orphans
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=ias_" | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=ias_vma_" | xargs -I {}  docker rm {}
docker:
	docker-compose down -v
	docker-compose rm -svf
	docker-compose up -d --build --remove-orphans
prune-all: docker-delete
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes