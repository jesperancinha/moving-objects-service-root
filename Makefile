SHELL=/bin/bash
GITHUB_RUN_ID ?=123
GRADLE_WRAPPER ?=7.6

.EXPORT_ALL_VARIABLES:
ISSUER_MF = $(shell echo $${ISSUER})
CLIENT_ID_MF = $(shell echo $${CLIENT_ID})
CLIENT_SECRET_MF = $(shell echo $${CLIENT_SECRET})
REDIRECT_PORT_MF = $(shell echo $${REDIRECT_PORT})

b: buildw build-app build-npm
build-gradle: buildw
build-npm:
	cd moving-objects-gui && yarn && npm run build
build-npm-cypress:
	cd e2e && yarn
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
	cd moving-objects-gui && npm run jest
wrapper:
	gradle wrapper --gradle-version ${GRADLE_WRAPPER}
build-app:
	gradle clean build test publishToMavenLocal
buildw-security:
	cd moving-objects-security-dsl && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
buildw-jwt-service:
	cd moving-objects-jwt-service && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
buildw-rest-service:
	cd moving-objects-rest-service && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && ./gradlew clean build assemble test jacocoTestReport publishToMavenLocal
buildw: buildw-security buildw-jwt-service buildw-rest-service
	gradle clean build
buildw-jwt-service-no-test:
	cd moving-objects-jwt-service && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && ./gradlew clean build -x test
generate-credentials:
	bash generateCredentials.sh
no-test: generate-credentials
	cd moving-objects-rest-service && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && ./gradlew clean build -x test
	make buildw-jwt-service-no-test
no-test-secure: generate-credentials
	cd moving-objects-security-dsl && make buildw
	cd moving-objects-rest-service && gradle wrapper --gradle-version ${GRADLE_WRAPPER} && gradle -Pprod clean build -x test
	make buildw-jwt-service-no-test
upgrade:
	gradle wrapper --gradle-version ${GRADLE_WRAPPER}
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
	docker-compose -p ${GITHUB_RUN_ID} up -d --build --remove-orphans
docker-action:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml up -d moving-objects-rest-service moving-objects-jwt-service mosdb influxdb prometheus nginx grafana
prune-all: docker-delete
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
update-snyk: update
	npm i -g snyk
update:
	gradle wrapper --gradle-version ${GRADLE_WRAPPER}
	npm install -g npm-check-updates
	cd moving-objects-gui && npx browserslist && ncu -u && yarn
audit:
	cd moving-objects-gui && npm audit fix && yarn
cypress-install:
	npm i -g cypress
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
	docker-compose -p ${GITHUB_RUN_ID} down --remove-orphans
	docker-compose -p ${GITHUB_RUN_ID} rm -fsva
	docker volume ls -qf dangling=true | xargs -I {} docker volume rm  {}
dcp:
	docker-compose -p ${GITHUB_RUN_ID} stop
dcup: dcd docker-clean docker objects-wait
dcup-full-action: dcd docker-clean no-test build-npm docker objects-wait
dcup-action: dcp docker-action objects-wait
dcup-light: dcd
	docker-compose -p ${GITHUB_RUN_ID} up -d mosdb
dcup-full-action-secure: dcd docker-clean credential-check no-test-secure build-npm-secure docker objects-wait

report:
	apt update -y
	apt install npm -y
	npm i -g yarn
	npm install -g n
	n stable
	cd moving-objects-gui && yarn && yarn add jest && npm run coverage
	make buildw && gradle -i
report-coverage:
	#gradle test jacocoTestReport publishToMavenLocal
	 ./gradlew clean build test jacocoTestReport -i
docker-stats:
	docker stats --all
build-nginx: build-npm
	docker-compose -p ${GITHUB_RUN_ID} stop nginx
	docker-compose -p ${GITHUB_RUN_ID} rm nginx
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache nginx
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-nginx-secure:
	docker-compose -p ${GITHUB_RUN_ID} stop nginx
	docker-compose -p ${GITHUB_RUN_ID} rm -fsv nginx
	make build-npm-secure
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache nginx
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-jwt-service: buildw-jwt-service
	docker-compose -p ${GITHUB_RUN_ID} stop moving-objects-jwt-service
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache moving-objects-jwt-service
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-rest-service-secure:
	docker-compose -p ${GITHUB_RUN_ID} stop moving-objects-rest-service
	docker-compose -p ${GITHUB_RUN_ID} rm -fsv moving-objects-rest-service
	make no-test-secure
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache moving-objects-rest-service
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-influxdb:
	docker-compose -p ${GITHUB_RUN_ID} stop influxdb
	docker-compose -p ${GITHUB_RUN_ID} rm influxdb
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache influxdb
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-grafana:
	docker-compose -p ${GITHUB_RUN_ID} stop grafana
	docker-compose -p ${GITHUB_RUN_ID} rm grafana
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache grafana
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-mosdb:
	docker-compose -p ${GITHUB_RUN_ID} stop mosdb
	docker-compose -p ${GITHUB_RUN_ID} rm mosdb
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache mosdb
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-prometheus:
	docker-compose -p ${GITHUB_RUN_ID} stop prometheus
	docker-compose -p ${GITHUB_RUN_ID} rm prometheus
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache prometheus
	docker-compose -p ${GITHUB_RUN_ID} up -d
node:
	sudo npm install -g n
	sudo n lts
shared-docker:
	sudo chmod 666 /var/run/docker.sock
end-logs:
	docker-compose -p ${GITHUB_RUN_ID} logs --tail 1000 moving-objects-jwt-service
	docker-compose -p ${GITHUB_RUN_ID} logs --tail 1000 moving-objects-rest-service
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
      --header "Authorization: Token ${INFLUX_TOKEN}" \
      --header "Content-Type: text/plain; charset=utf-8" \
      --header "Accept: application/json" \
      --data-binary 'objects,sensor_group=GR1 temperature=$(shell date +%s%n | cut -b7-8),humidity=$(shell date +%s%n | cut -b8-9) $(shell date -u +%s000000000)'
start-telegraf:
	cd docker-files/telegraf && make start-telegraf
start-telegraf-container:
	cd docker-files/telegraf && make start-telegraf-container
logs-telegraf-container:
	cd docker-files/telegraf && make logs-telegraf-container
up:
	docker-compose -p ${GITHUB_RUN_ID} up -d
continue-demo: up cypress-electron start-telegraf-container
start-demo: dcup-full-action continue-demo
create-demo-secure-credentials:
	cd moving-objects-rest-service && make var-export
continue-secure-build: build-rest-service-secure build-nginx-secure
start-demo-secure: dcup-full-action credential-check continue-demo continue-secure-build
analysis:
	df -hi
	df -h
stop-jars:
	ps -fx | grep "java -jar moving-objects-rest-service/build/libs/moving-objects-rest-service.jar" | grep -v grep | head -n 1 | cut -d' ' -f4 | xargs kill &
	ps -fx | grep "java -jar moving-objects-rest-service/build/libs/moving-objects-rest-service.jar" | grep -v grep | head -n 1 | cut -d' ' -f5 | xargs kill &
	ps -fx | grep "java -jar moving-objects-rest-service/build/libs/moving-objects-rest-service.jar" | grep -v grep
okta-restart: stop-jars
	make no-test-secure
	java -jar moving-objects-rest-service/build/libs/moving-objects-rest-service.jar &
	cd moving-objects-gui && npm run start-prod
credential-check:
	if [ -z "$(ISSUER_MF)" ] || [ -z "$(CLIENT_ID_MF)" ] || [ -z "$(CLIENT_SECRET_MF)" ] || [ -z "$(REDIRECT_PORT_MF)" ]; then \
	echo -e "\033[1;31m--- Configuration Failure ---, you need to configure ISSUER, CLIENT_ID, CLIENT_SECRET and REDIRECT_PORT\033[0m"; \
	make create-demo-secure-credentials; \
	exit 1; \
	fi
install:
	npm i -g jest
local-pipeline: install generate-credentials build-gradle build-npm test-gradle test-node report-coverage
