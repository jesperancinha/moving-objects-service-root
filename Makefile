b: buildw build-app build-npm
build-gradle: buildw
build-npm:
	cd international-airports-gui && yarn
wrapper:
	gradle wrapper
build-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd international-airports-rest/international-airports-rest-service && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
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
update-snyk: update
	npm i -g snyk
update:
	npm install -g npm-check-updates
	cd international-airports-gui && npx browserslist && ncu -u && yarn
audit:
	cd international-airports-gui && npm audit fix && yarn
cypress-open:
	cd e2e && yarn && npm run cypress
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-edge:
	cd e2e && make cypress-edge
