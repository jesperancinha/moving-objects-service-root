SHELL=/bin/bash

b: buildw build-app build-npm
build-gradle: buildw
build-npm:
	cd moving-objects-gui && yarn
build-npm-dist: build-npm
	cd moving-objects-gui && npm run build
build-npm-docker:
	bash generateCredentials.sh
	cd moving-objects-gui && [ -d node_modules ] || mkdir node_modules
	cd moving-objects-gui && chmod 777 node_modules
	cd e2e && [ -d node_modules ] || mkdir node_modules
	cd e2e && chmod 777 node_modules
	touch moving-objects-gui/yarn.lock
	chmod 777 moving-objects-gui
	chmod 777 moving-objects-gui/yarn.lock
	touch e2e/yarn.lock
	chmod 777 e2e
	chmod 777 e2e/yarn.lock
	docker-compose build gui-builder
	docker-compose up gui-builder
wrapper:
	gradle wrapper
build-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd moving-objects-jwt-service && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build && gradle assemble test jacocoTestReport publishToMavenLocal
	gradle clean build
	gradle test jacocoTestReport publishToMavenLocal
no-test:
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build -x test
	cd moving-objects-jwt-service && gradle wrapper && ./gradlew clean build -x test
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
dcup-full-action: dcd docker-clean no-test build-npm docker objects-wait
dcup-action: dcd docker objects-wait
report:
	apt update -y
	apt install npm -y
	npm i -g yarn
	npm install -g n
	n stable
	cd moving-objects-gui && yarn && yarn add jest && npm run coverage
	make buildw && gradle -i
