SHELL=/bin/bash

b: buildw build-app build-npm
build-gradle: buildw
build-npm:
	cd moving-objects-gui && yarn
build-npm-dist: build-npm
	cd moving-objects-gui && npm run build
build-npm-docker:
	docker build . -t moving-objects-gui-image
	docker run --name moving-objects-gui-build -v "$(shell pwd)"/moving-objects-gui:/opt/moving-objects-gui -v "$(shell pwd)"/moving-objects-gui/yarn-error.log:/opt/moving-objects-gui/yarn-error.log -v "$(shell pwd)"/moving-objects-gui/node_modules:/opt/moving-objects-gui/node_modules -v "$(shell pwd)"/moving-objects-gui/dist:/opt/moving-objects-gui/dist --user 1000:1000 --entrypoint '/bin/sh' moving-objects-gui-image -c 'cd /opt/moving-objects-gui && yarn && npm run build-docker'
wrapper:
	gradle wrapper
build-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd moving-objects-oauth-service && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	gradle clean build
	gradle test jacocoTestReport publishToMavenLocal
no-test:
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build -x test
	cd moving-objects-oauth-service && gradle wrapper && ./gradlew clean build -x test
upgrade:
	gradle wrapper --gradle-version 7.4
upgrade-mac-os:
	brew upgrade gradle
	sdk install gradle
stop:
	docker-compose down --remove-orphans
docker-clean:
	docker-compose rm -svf
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=mos_" | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=mos_" | xargs -I {}  docker rm {}
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
	cd moving-objects-gui && npx browserslist && ncu -u && yarn
audit:
	cd moving-objects-gui && npm audit fix && yarn
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
objects-wait:
	bash objects_wait.sh
dcd:
	pwd
	docker-compose down --remove-orphans
dcup: dcd docker-clean docker objects-wait
dcup-full-action: dcd docker-clean no-test build-npm-docker docker objects-wait
