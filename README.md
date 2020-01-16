International Airports Service
=================

[![Generic badge](https://img.shields.io/static/v1.svg?label=BitBucket&message=International%20Airports&color=informational)](https://bitbucket.org/jesperancinha/international-airports-service-root) 
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a57b110faf0844739a274888b38e0f6e)](https://www.codacy.com?utm_source=bitbucket.org&amp;utm_medium=referral&amp;utm_content=jesperancinha/international-airports-service-root&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/bb/jesperancinha/international-airports-service-root.svg?style=svg)](https://circleci.com/bb/jesperancinha/international-airports-service-root)
[![Build status](https://ci.appveyor.com/api/projects/status/lfauvt61j0nw5xkg?svg=true)](https://ci.appveyor.com/project/jesperancinha/international-airports-service-root)
[![Bitbucket Pipelines branch](https://img.shields.io/bitbucket/pipelines/jesperancinha/international-airports-service-root/master)](https://bitbucket.org/jesperancinha/international-airports-service-root/addon/pipelines/home)

# Introduction

The goal of this mini-project is to introduce you to two important monitoring tools in the software landscape
These are prometheus and grafana.
In this project you will find implementations using micrometer and a store for metrics.
This application consists of an option to calculate the distance between airports. It calculates:

-   The distance between airports
-   The price you are paying per ticked
-   A filtered table that provides you with all airport details

These applications are implemented in a Reactive way with WebFlux and the Front End is implemented with Angular Materials.
We will use as base the [RapidAPI](https://rapidapi.com/) end points and convert them to our proprietary format.

# How to build

To start and build the spring boot process:

`./gradlew bootRun`

# How to run

Please make the front end is build before anything:

```bash
sdk use java 12.0.2.hs-adpt
cd travel-gui
npm install
npm run build
cd ..
./gradlew bootRun
```
# Endpoint list

-   [Actuator](http://localhost:9000/travel/actuator)
-   [Actuator Updates for Spring Boot 2](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2.0-M3-Release-Notes#actuator-http-trace-and-auditing-are-disabled-by-default)
-   [All Requests](http://localhost:9000/travel/actuator/metrics/http.server.requests)
-   [Ok Requests 200](http://localhost:9000/travel/actuator/metrics/http.server.requests?tag=status:200)
-   [Ok Requests 400](http://localhost:9000/travel/actuator/metrics/http.server.requests?tag=status:400)
-   [Ok Requests 500](http://localhost:9000/travel/actuator/metrics/http.server.requests?tag=status:500)

# References

-   [Travel Web Cams](https://rapidapi.com/webcams.travel/api/webcams-travel)
-   [Airport Finder](https://rapidapi.com/cometari/api/airportsfinder)
-   [RapidAPI](https://rapidapi.com/)  
-   [Postman OAuth 2.0](https://learning.getpostman.com/docs/postman/sending-api-requests/authorization/#oauth-20)

## About me

[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social)](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED)](http://joaofilipesabinoesperancinha.nl)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=green)](https://dev.to/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Medium&message=@jofisaes&color=green)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackernoon&message=@jesperancinha&color=green)](https://hackernoon.com/@jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Free%20Code%20Camp&message=jofisaes&color=008000)](https://www.freecodecamp.org/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackerrank&message=jofisaes&color=008000)](https://www.hackerrank.com/jofisaes)
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
