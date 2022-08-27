SHELL=/bin/bash

.EXPORT_ALL_VARIABLES:
INFLUX_TOKEN = $(shell echo $${INFLUX_TOKEN})

start-telegraf-test:
	telegraf --config telegraf.conf --test
start-telegraf:
	echo $(INFLUX_TOKEN)
	if [ -z "$(INFLUX_TOKEN)" ]; then \
  	export INFLUX_TOKEN=$(shell cat token); \
	telegraf --config telegraf.conf; \
	else \
	telegraf --config telegraf.conf; \
	fi
