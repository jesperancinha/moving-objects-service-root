SHELL=/bin/bash

.EXPORT_ALL_VARIABLES:
INFLUX_TOKEN_MF = $(shell echo $${INFLUX_TOKEN})

start-telegraf-test:
	telegraf --config telegraf.conf --test
start-telegraf:
	echo $(INFLUX_TOKEN_MF)
	if [ -z "$(INFLUX_TOKEN_MF)" ]; then \
  	export INFLUX_TOKEN=$(shell cat token); \
	telegraf --config telegraf.conf; \
	else \
	telegraf --config telegraf.conf; \
	fi
start-telegraf-container:
	docker-compose -f ../../docker-compose.yml -f docker-compose.yml --env-file .env up -d --remove-orphans telegraf
stop-telegraf-container:
	docker-compose -f ../../docker-compose.yml -f docker-compose.yml --env-file .env stop telegraf
	docker-compose -f ../../docker-compose.yml -f docker-compose.yml --env-file .env rm telegraf
logs-telegraf-container:
	docker-compose -f ../../docker-compose.yml -f docker-compose.yml logs telegraf
