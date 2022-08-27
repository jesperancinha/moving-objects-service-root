SHELL=/bin/bash

b: buildw build-app build-npm
build-gradle: buildw
build-npm:
	cd moving-objects-gui && yarn && npm run build-docker
build-npm-secure:
	cd moving-objects-gui && yarn && npm run build-prod
build-npm-dist: build-npm
	cd moving-objects-gui && npm run build
build-npm-docker:
	cd moving-objects-gui && [ -d node_modules ] || mkdir node_modules
	cd moving-objects-gui && chmod 777 node_modules
	touch moving-objects-gui/yarn.lock
	chmod 777 moving-objects-gui
	chmod 777 moving-objects-gui/yarn.lock
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml build gui-builder
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from gui-builder gui-builder
build-npm-cypress-docker:
	cd e2e && [ -d node_modules ] || mkdir node_modules
	cd e2e && chmod 777 node_modules
	touch e2e/yarn.lock
	chmod 777 e2e
	chmod 777 e2e/yarn.lock
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml build cypress-builder
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from cypress-builder cypress-builder
test-gradle:
	 ./gradlew test
test: test-node test-gradle
test-node:
	cd moving-objects-gui ;\
	yarn install ;\
	npm run test ;\
	cd ..
wrapper:
	gradle wrapper
build-app:
	gradle clean build test publishToMavenLocal
buildw:
	cd moving-objects-security-dsl && gradle wrapper && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
	cd moving-objects-jwt-service && gradle wrapper && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
	cd moving-objects-jwt-service && gradle wrapper && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
	gradle clean build
	gradle test jacocoTestReport publishToMavenLocal
buildw-jwt-service:
	cd moving-objects-jwt-service && gradle wrapper && ./gradlew clean build -x test
generate-credentials:
	bash generateCredentials.sh
no-test: generate-credentials
	cd moving-objects-rest-service && gradle wrapper && ./gradlew clean build -x test
	make buildw-jwt-service
no-test-secure: generate-credentials
	cd moving-objects-security-dsl && make buildw
	cd moving-objects-rest-service && gradle wrapper && gradle -Pprod clean build -x test
	make buildw-jwt-service
upgrade:
	gradle wrapper --gradle-version 7.4
upgrade-mac-os:
	brew upgrade gradle
	sdk install gradle
stop: docker-clean
	docker-compose down --remove-orphans
docker-clean:
	docker-compose down -v
	docker-compose rm -svf
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=mos_" | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=mos_" | xargs -I {}  docker rm {}
docker:
	docker-compose up -d --build --remove-orphans
docker-action: build-npm-docker
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up -d moving-objects-rest-service moving-objects-jwt-service mosdb influxdb prometheus nginx grafana
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
cypress-install:
	cd e2e && make build
cypress-open:
	cd e2e && make cypress-open
cypress-open-docker:
	cd e2e && make cypress-open-docker
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-firefox-full:
	cd e2e && make cypress-firefox-full
cypress-edge:
	cd e2e && make cypress-edge
objects-wait:
	bash objects_wait.sh
dcd:
	docker-compose down --remove-orphans
dcp:
	docker-compose stop
dcup: dcd docker-clean docker objects-wait
dcup-full-action: dcd docker-clean no-test build-npm docker objects-wait
dcup-action: dcp docker-action objects-wait
dcup-light: dcd
	docker-compose up -d mosdb
dcup-full-action-secure: dcd docker-clean no-test-secure build-npm-secure docker objects-wait

report:
	apt update -y
	apt install npm -y
	npm i -g yarn
	npm install -g n
	n stable
	cd moving-objects-gui && yarn && yarn add jest && npm run coverage
	make buildw && gradle -i
report-coverage:
	 ./gradlew clean build test jacocoTestReport -i
docker-stats:
	docker stats --all
build-nginx: build-npm
	docker-compose stop nginx
	docker-compose rm nginx
	docker-compose build --no-cache nginx
	docker-compose up -d
build-jwt-service: buildw-jwt-service
	docker-compose stop moving-objects-jwt-service
	docker-compose build --no-cache moving-objects-jwt-service
	docker-compose up -d
build-influxdb:
	docker-compose stop influxdb
	docker-compose rm influxdb
	docker-compose build --no-cache influxdb
	docker-compose up -d
build-grafana:
	docker-compose stop grafana
	docker-compose rm grafana
	docker-compose build --no-cache grafana
	docker-compose up -d
build-mosdb:
	docker-compose stop mosdb
	docker-compose rm mosdb
	docker-compose build --no-cache mosdb
	docker-compose up -d
build-prometheus:
	docker-compose stop prometheus
	docker-compose rm prometheus
	docker-compose build --no-cache prometheus
	docker-compose up -d
node:
	sudo npm install -g n
	sudo n lts
shared-docker:
	sudo chmod 666 /var/run/docker.sock
end-logs:
	docker-compose logs --tail 1000 moving-objects-jwt-service
	docker-compose logs --tail 1000 moving-objects-rest-service
renovate:
	docker run \
		--rm \
		-e LOG_LEVEL="debug" \
		-e RENOVATE_CONFIG_FILE="/usr/src/app/renovate.json" \
		-e RENOVATE_TOKEN=${RENOVATE_TOKEN} \
		-v "${PWD}/renovate.json:/usr/src/app/renovate.json" \
		renovate/renovate:latest
influx-db-example:
	curl -i --request POST \
    "http://localhost:8086/api/v2/write?org=Moving%20Objects&bucket=mos&precision=ns" \
      --header "Authorization: Token ${INFLUX_DB_API_TOKEN}" \
      --header "Content-Type: text/plain; charset=utf-8" \
      --header "Accept: application/json" \
      --data-binary 'objects,sensor_group=GR1 temperature=$(shell date +%s%n | cut -b7-8),humidity=$(shell date +%s%n | cut -b8-9) $(shell date -u +%s000000000)'
