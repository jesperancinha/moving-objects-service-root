# International Airports Service

---
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/rapidAPI-50.png "RapidAPI")](https://rapidapi.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-50.png "Docker")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-compose-50.png "Docker Compose")](https://docs.docker.com/compose/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/java-50.png "Java")](https://www.oracle.com/nl/java/ )
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-webflux-50.png "Spring Webfllux")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-reactor-50.png "Spring Reactor")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/postgres-50.png "Postgres")](https://www.postgresql.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/nodejs-50.png "NodeJS")](https://nodejs.org/en/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/bash-50.png "Bash")](https://www.gnu.org/software/bash/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/npm-50.png "NPM")](https://www.npmjs.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/yarn-50.png "Yarn")](https://yarnpkg.com/)

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=BitBucket&message=International%20Airports&color=informational)](https://bitbucket.org/jesperancinha/international-airports-service-root) 
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a57b110faf0844739a274888b38e0f6e)](https://www.codacy.com?utm_source=bitbucket.org&amp;utm_medium=referral&amp;utm_content=jesperancinha/international-airports-service-root&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/bb/jesperancinha/international-airports-service-root.svg?style=svg)](https://circleci.com/bb/jesperancinha/international-airports-service-root)
[![Build status](https://ci.appveyor.com/api/projects/status/lfauvt61j0nw5xkg?svg=true)](https://ci.appveyor.com/project/jesperancinha/international-airports-service-root)
[![Bitbucket Pipelines branch](https://img.shields.io/bitbucket/pipelines/jesperancinha/international-airports-service-root/master)](https://bitbucket.org/jesperancinha/international-airports-service-root/addon/pipelines/home)

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

## Setup

In order to run this project we need to fulfill some requirements:

1. Have an IDE
2. Have JDK4 installed (Details on how to do that in this [manual](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Hints%26Tricks.md))
3. Have [Docker desktop](https://www.docker.com/products/docker-desktop) installed.

We do not need a special machine to do that. 
In the old days, we used to have something called docker-machine that we had to use directly.
With docker-desktop, this isn't necessary anymore for local usage.

Upgrade gradle:

```bash
brew upgrade gradle

sdk install java 14.0.1.hs-adpt

sdk install gradle 6.5
```

## Settings

### international-airports-sst-live
This service is fixed to work as the main single source service.

Login system is OAuth2.

Metric end points are completely open.

-   Port: 8081

### international-airports-sst-mock
This service is fixed to work as the alternative to the main single source service.

Login system is OAuth2

Metric end points are completely open.

-   Port: 8081

### international-airports-rest-service
This is the service which will serve the user and the front end.

There is no login service. It is completely anonymous

Metric end points are completely open.

-   Port: 8082

## How to build and run the Spring boot services:

To start and build a spring boot process, just go to the root of that project and run:

`sdk use java 12.0.2.hs-adpt`

`./gradlew bootRun`

## How to build and run the Front End service:

`npm install`

`npm run build`

## How to run everything with Docker compose in one go:

Make a copy of the [.env-template](.env-template) file to [.env](.env) in the same folder.

Replace those variables with your keys from your [RapidAPI](https://rapidapi.com/) account.
The keys you will need come from these two applications:

-   AIRPORTS_KEY - [Airport Finder](https://rapidapi.com/cometari/api/airportsfinder)
-   WEBCAMS_KEY - [WebCam Travel](https://rapidapi.com/webcams.travel/api/webcams-travel)

## How to test the main image

There is a script called [build-standalone.sh](./build-standalone.sh).

This script ensures that that only the container for the application services is run.

Just run:

`build-standalone.sh ${AIRPORTS_KEY} ${WEBCAMS_KEY}`

Please replace the variables with the matching keys. For this script, it is necessary to pass on the keys as arguments.

Here is an example:

`build-standalone.sh ksdnfklsnknsdpnskdnvslkdnkslnnvskdnvlsnklnksvdvdkn 34j98ru390j3934jr93r9i3rj3ig90jjbndn90jb3099949j3t`

## Docker images

This tutorial makes use of the following docker images:

[![dockeri.co](https://dockeri.co/image/jesperancinha/je-all-build-jdk-14)](https://hub.docker.com/r/jesperancinha/je-all-build-jdk-14)

[![dockeri.co](https://dockeri.co/image/grafana/grafana)](https://hub.docker.com/r/grafana/grafana)

[![dockeri.co](https://dockeri.co/image/influxdb)](https://hub.docker.com/r/influxdb)

[![dockeri.co](https://dockeri.co/image/prom/prometheus)](https://hub.docker.com/r/prom/prometheus)


## Url Example List:

It's important to notice that `localhost` may change depending on the environment or if docker compose is being used.

>http://localhost:8082/iairports/airportwebcams/term/Amsterdam
>
>http://localhost:8082/iairports/airportwebcams/code/AMS
>
>http://localhost:8082/iairports/airports/term/Amsterdam/100
>
>http://localhost:8082/iairports/airports/code/AMS
>
>http://localhost:8082/iairports/airports/code/AMS
>
>http://localhost:8082/iairports/webcams/location/52.376610/4.892629/10
>
>http://localhost:8082/iairports/webcams/page/0/50

## Endpoint list

-   [Actuator](http://localhost:8082/iairports/actuator)
-   [Actuator Updates for Spring Boot 2](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2.0-M3-Release-Notes#actuator-http-trace-and-auditing-are-disabled-by-default)
-   [All Requests](http://localhost:8082/iairports/actuator/metrics/http.server.requests)
-   [Ok Requests 200](http://localhost:8082/iairports/actuator/metrics/http.server.requests?tag=status:200)
-   [Ok Requests 400](http://localhost:8082/iairports/actuator/metrics/http.server.requests?tag=status:400)
-   [Ok Requests 500](http://localhost:8082/iairports/actuator/metrics/http.server.requests?tag=status:500)

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

## Tools & Tips

### InfluxDB

```bash
brew install influxdb
```

### Git tagging
```bash
git tag new-tag old-tag
git tag -d old-tag
git push origin :refs/tags/old-tag
git push --tags
git pull --prune --tags
```

## About me 👨🏽‍💻🚀

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/acclaim-20.png "Acclaim")](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/instagram-20.png "Instagram")](https://www.instagram.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
