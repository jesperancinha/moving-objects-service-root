# International Airports Service

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
-   The price you are paying per ticked
-   A filtered table that provides you with all airport details
-   A list of the webcams nearby

These applications are implemented in a Reactive way with WebFlux and the Front End is implemented with Angular Materials.
We will use as base the [RapidAPI](https://rapidapi.com/) end points and convert them to our proprietary format.

This project is also the official support project of my article on medium:

[Monitoring with Grafana, Prometheus and InfluxDB — An airport webcams example](https://medium.com/swlh/monitoring-with-grafana-prometheus-and-influxdb-an-airport-webcams-example-508c04b226b6)

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

If you run the following script you are expected to have a docker-machine installed with the name dev.

Please read the installation manual for docker if you have any questions on how to run.

`./build.sh`

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

[![dockeri.co](https://dockeri.co/image/jesperancinha/je-all-build-jdk-12)](https://hub.docker.com/r/jesperancinha/je-all-build-jdk-12)

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

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png)](http://joaofilipesabinoesperancinha.nl)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social)](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=On%20Medium&color=purple)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=On%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/LossArticles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=BitBucket&message=jesperancinha&color=navy)](https://bitbucket.org/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=jesperancinha&color=navy)](https://gitlab.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED)](http://joaofilipesabinoesperancinha.nl)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=green)](https://dev.to/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Medium&message=@jofisaes&color=green)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackernoon&message=@jesperancinha&color=green)](https://hackernoon.com/@jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Free%20Code%20Camp&message=jofisaes&color=008000)](https://www.freecodecamp.org/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackerrank&message=jofisaes&color=008000)](https://www.hackerrank.com/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Forces&message=jesperancinha&color=008000)](https://codeforces.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Coder%20Byte&message=jesperancinha&color=008000)](https://coderbyte.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Wars&message=jesperancinha&color=008000)](https://www.codewars.com/users/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Acclaim%20Badges&message=joao-esperancinha&color=red)](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=orange)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Pen&message=jesperancinha&color=orange)](https://codepen.io/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Docker%20Images&message=jesperanciha&color=099CEC)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/DockerImages.md)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png)](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png)](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png)](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/instagram-20.png)](https://www.instagram.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png)](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png)](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
