# Moving Objects Service

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=Checkout%20this%20@github%20repo%20by%20@joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB:%20https://github.com/jesperancinha/moving-objects-service-root)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Moving%20Objects%20Cams%20Service%20💎&color=informational)](https://github.com/jesperancinha/moving-objects-service-root)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![mos-app](https://github.com/jesperancinha/moving-objects-service-root/actions/workflows/mos-app.yml/badge.svg)](https://github.com/jesperancinha/moving-objects-service-root/actions/workflows/mos-app.yml)
[![e2e-mos-app](https://github.com/jesperancinha/moving-objects-service-root/actions/workflows/mos-app-e2e.yml/badge.svg)](https://github.com/jesperancinha/moving-objects-service-root/actions/workflows/mos-app-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b968dd9bada749e59b30125dba38f97e)](https://www.codacy.com/gh/jesperancinha/moving-objects-service-root/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/moving-objects-service-root&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/b968dd9bada749e59b30125dba38f97e)](https://www.codacy.com/gh/jesperancinha/moving-objects-service-root/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/moving-objects-service-root&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/moving-objects-service-root/branch/main/graph/badge.svg?token=rtZSBWhccP)](https://codecov.io/gh/jesperancinha/moving-objects-service-root)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/moving-objects-service-root/badge.svg?branch=main)](https://coveralls.io/github/jesperancinha/moving-objects-service-root?branch=main)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/moving-objects-service-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/moving-objects-service-root.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/moving-objects-service-root.svg)](#)

---


## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Introduction

The goal of this mini-project is to introduce you to three important monitoring tools and data stores used in the software landscape.
These are prometheus, grafana and influxDB.
In this project you will find implementations using micrometer and a store for metrics.
These modules have been implemented in a Reactive way with WebFlux and the Front End is implemented with Angular Materials.

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://itnext.io/monitoring-secure-coroutines-and-webflux-reactive-applications-with-prometheus-grafana-and-b99dafb59a4b) [Monitoring Secure Coroutines and WebFlux Reactive applications with Prometheus, Grafana, and InfluxDB — A webcams example](https://itnext.io/monitoring-secure-coroutines-and-webflux-reactive-applications-with-prometheus-grafana-and-b99dafb59a4b)

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/moving-objects-service-root/tree/1.0.0) - [8af14c9c60c9275cac1814238b9c9b0a73a427f2](https://github.com/jesperancinha/moving-objects-service-root/tree/1.0.0)

## Setup

In order to run this project we need to fulfill some requirements:

1.  Have an IDE
2.  Have JDK17 installed (Details on how to do that in this [manual](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Hints%26Tricks.md))
3.  Have [Docker desktop](https://www.docker.com/products/docker-desktop) installed or just use a Linux machine with docker-compose.

##### MAC-OS Installation

```bash
brew upgrade gradle
sdk install java 17-open
sdk use java 17-open
sdk install gradle 7.3.3
```

## How to run

#### Open Version

```shell
make dcup-full-action
```

#### Secure version

>Does not work with Cypress, and it needs to be configured. Check the [Okta manual](./OktaManual.md) of this project for more details.


```shell
export REDIRECT_PORT=8080
make dcup-full-action-secure
```

#### Full Demo

These Cypress tests not only perform tests, but they also generate token files necessary to allow [Telegraf](https://github.com/influxdata/telegraf) to access InfluxDB. These are:

1.  [docker-files/telegraf/.env](./docker-files/telegraf/.env)
2.  [docker-files/telegraf/token](./docker-files/telegraf/token)

The full demo Makefile script ensures that:

1.  The services start
2.  Cypress tests run
3.  Telegraf runs on a separate container

It can take anything between 5 and 10 minutes to get all the containers going, but then you'll get:

1.  Working InfluxDB scrappers: Pull from prometheus endpoints directly to InfluxDB
2.  Working [Telegraf](https://github.com/influxdata/telegraf) scrappers: Pull from prometheus and pushes data to Influx DB 

## Run Cypress

#### Against Nginx

```shell
make cypress-open-docker
```

#### Directly against the service ports

```shell
make cypress-open
```

## Endpoint list

#### Direct

-   [Actuator](http://localhost:8082/objects/actuator/)
-   [Actuator Trace Requests](http://localhost:8082/aggregator/actuator/httptrace)
-   [All Requests](http://localhost:8082/objects/actuator/metrics/http.server.requests)
-   [Ok Requests 200](http://localhost:8082/objects/actuator/metrics/http.server.requests?tag=status:200)
-   [Ok Requests 400](http://localhost:8082/objects/actuator/metrics/http.server.requests?tag=status:400)
-   [Ok Requests 500](http://localhost:8082/objects/actuator/metrics/http.server.requests?tag=status:500)
-   [Grafana Login](http://localhost:3000/login)
-   [Prometheus](http://localhost:9090/graph)
-   [Influx DB Onboarding](http://localhost:8086/onboarding)
-   [Objects API Swagger UI](http://localhost:8082/objects/webjars/swagger-ui/index.html) - `/objects/v3/api-docs`

-   [Prometheus for JWT REST](http://localhost:8081/objects/actuator/prometheus)
-   [Prometheus for Okta REST](http://localhost:8082/aggregator/actuator/prometheus)
-   [Prometheus for NGINX](http://localhost:4000/metrics)

-   [Trace for JWT REST](http://localhost:8081/objects/actuator/metrics/http.server.requests)

#### Via NGINX

-   [Actuator](http://localhost:8080/objects/actuator/)
-   [Actuator Trace Requests](http://localhost:8080/aggregator/actuator/httptrace)
-   [All Requests](http://localhost:8080/objects/actuator/metrics/http.server.requests)
-   [Ok Requests 200](http://localhost:8080/objects/actuator/metrics/http.server.requests?tag=status:200)
-   [Ok Requests 400](http://localhost:8080/objects/actuator/metrics/http.server.requests?tag=status:400)
-   [Ok Requests 500](http://localhost:8080/objects/actuator/metrics/http.server.requests?tag=status:500)
-   [Objects API Swagger UI](http://localhost:8080/objects/webjars/swagger-ui/index.html) - `/objects/v3/api-docs`

-   [Prometheus for JWT REST](http://localhost:8080/objects/actuator/prometheus)
-   [Prometheus for Okta REST](http://localhost:8080/aggregator/actuator/prometheus)
-   [Prometheus for NGINX](http://localhost:8080/metrics)

-   [Trace for JWT REST](http://localhost:8080/objects/actuator/metrics/http.server.requests)

#### Over Actuator

-   [Actuator Updates for Spring Boot 2](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2.0-M3-Release-Notes#actuator-http-trace-and-auditing-are-disabled-by-default)

## References

-   [Micrometer Prometheus](https://micrometer.io/docs/registry/prometheus)
-   [How to Configure an OAuth2 Authentication With Spring Security (Part 1)](https://dzone.com/articles/how-to-configure-an-oauth2-authentication-with-spr)
-   [Grafana Installing using Docker](https://grafana.com/docs/grafana/latest/installation/docker/)
-   [Prometheus.IO](https://prometheus.io/)
-   [Prometheus parameter examples](https://github.com/prometheus/prometheus/blob/release-2.15/config/testdata/conf.good.yml)
-   [Travel Web Cams](https://rapidapi.com/webcams.travel/api/webcams-travel)
-   [Airport Finder](https://rapidapi.com/cometari/api/airportsfinder)
-   [RapidAPI](https://rapidapi.com/)
-   [Postman OAuth 2.0](https://learning.getpostman.com/docs/postman/sending-api-requests/authorization/#oauth-20)
-   [How do you rename a Git tag?](https://stackoverflow.com/questions/1028649/how-do-you-rename-a-git-tag)
-   [The 10 Best Software Engineering Books in 2019](https://devconnected.com/the-10-best-software-engineering-books-in-2019/)
-   [Using Prometheus with InfluxDB for metrics storage](https://www.percona.com/live/e17/sites/default/files/slides/Using%20Prometheus%20with%20InfluxDB%20for%20Metrics%20Storage%20-%20FileId%20-%20115469.pdf)
-   [Data exploration using InfluxQL](https://docs.influxdata.com/influxdb/v1.7/query_language/data_exploration/)
-   [Monitoring nginx with Prometheus and Grafana by Dimitri](https://dimitr.im/monitoring-nginx-with-prometheus-and-grafana)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
