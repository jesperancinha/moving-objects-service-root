# Moving Objects Service

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=Moving%20Objects%20Cams%20Service%20💎&color=informational)](https://gitlab.com/jesperancinha/moving-objects-service-root)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Gitlab pipeline status (branch)](https://img.shields.io/gitlab/pipeline/jesperancinha/video-series-app/master)](https://gitlab.com/jesperancinha/video-series-app/-/pipelines)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/7ab2df0b62f74416ab97d2da2bf31013)](https://www.codacy.com/gl/jesperancinha/moving-objects-service-root/dashboard?utm_source=gitlab.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/moving-objects-service-root&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/7ab2df0b62f74416ab97d2da2bf31013)](https://www.codacy.com/gl/jesperancinha/moving-objects-service-root/dashboard?utm_source=gitlab.com&utm_medium=referral&utm_content=jesperancinha/moving-objects-service-root&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/gitlab/jesperancinha/moving-objects-service-root/badge.svg?branch=main)](https://coveralls.io/gitlab/jesperancinha/moving-objects-service-root?branch=main)
[![codecov](https://codecov.io/gl/jesperancinha/moving-objects-service-root/branch/main/graph/badge.svg?token=6XDAT2GXGI)](https://codecov.io/gl/jesperancinha/moving-objects-service-root)

---

## Tech stack

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/gradle-50.png "Gradle")](https://gradle.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-50.png "Docker")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-compose-50.png "Docker Compose")](https://docs.docker.com/compose/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/java-50.png "Java")](https://www.oracle.com/nl/java/ )
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-webflux-50.png "Spring Webfllux")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-reactor-50.png "Spring Reactor")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/nodejs-50.png "NodeJS")](https://nodejs.org/en/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/bash-50.png "Bash")](https://www.gnu.org/software/bash/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/npm-50.png "NPM")](https://www.npmjs.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/yarn-50.png "Yarn")](https://yarnpkg.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/javascript-50.png "Javascript")](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/typescript-50.png "Javascript")](https://www.typescriptlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/swagger-50.png "Swagger")](https://swagger.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/cypress-50.png "Cypress")](https://www.cypress.io/)

---

## Introduction

The goal of this mini-project is to introduce you to two important monitoring tools in the software landscape
These are prometheus and grafana.
In this project you will find implementations using micrometer and a store for metrics.
This application consists of an option to calculate the distance between airports. It calculates:

-   The distance between airports
-   The price you are paying per ticket
-   A filtered table that provides you with all airport details
-   A list of the webcams nearby

These applications have been implemented in a Reactive way with WebFlux and the Front End is implemented with Angular Materials.
And that's awesome!
We will use the free restservices provided by the [RapidAPI](https://rapidapi.com/) end points and then convert them to our proprietary format.

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/swlh/monitoring-with-grafana-prometheus-and-influxdb-an-airport-webcams-example-508c04b226b6) [Monitoring with Grafana, Prometheus and InfluxDB — An airport webcams example](https://medium.com/swlh/monitoring-with-grafana-prometheus-and-influxdb-an-airport-webcams-example-508c04b226b6)

> Please read the first paragraph to be up-to-date with the complete changes this repo is undergoing

[![alt img](./docs/images/articles.international.airports.intro..png)](https://medium.com/swlh/monitoring-with-grafana-prometheus-and-influxdb-an-airport-webcams-example-508c04b226b6)
## Setup

In order to run this project we need to fulfill some requirements:

1.  Have an IDE
2.  Have JDK17 installed (Details on how to do that in this [manual](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Hints%26Tricks.md))
3.  Have [Docker desktop](https://www.docker.com/products/docker-desktop) installed.


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
make dcup-full-action-secure
```

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

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bmc-20.png "Buy me a Coffe")](https://www.buymeacoffee.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=WWW&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/leet-code-20.png "LeetCode")](https://leetcode.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-earth-20.png "Hacker Earth")](https://www.hackerearth.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/khan-academy-20.png "Khan Academy")](https://www.khanacademy.org/profile/jofisaes)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![VMware Spring Professional 2021](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/vmware-spring-professional-2021-20.png "VMware Spring Professional 2021")](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-20.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-20.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![IBM Cybersecurity Analyst Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cybersecurity-analyst-professional-certificate-20.png "IBM Cybersecurity Analyst Professional")](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![Certified Advanced JavaScript Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/cancanit-badge-1462-20.png "Certified Advanced JavaScript Developer")](https://cancanit.com/certified/1462/)
[![Certified Neo4j Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/professional_neo4j_developer-20.png "Certified Neo4j Professional")](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning-20.png "Deep Learning")](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
