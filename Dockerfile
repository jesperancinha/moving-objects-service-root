FROM openjdk:17-slim-buster

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY international-airports-rest/international-airports-rest-service/build/libs/international-airports-rest-service-1.0.0.jar ${runningFolder}

COPY international-airports-sst/international-airports-sst-live/build/libs/international-airports-sst-live-1.0.0.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ARG AIRPORTS_KEY

ARG WEBCAMS_KEY

ENV AIRPORTS_KEY=${AIRPORTS_KEY}

ENV WEBCAMS_KEY=${WEBCAMS_KEY}

ENTRYPOINT ["entrypoint.sh","${AIRPORTS_KEY}", "${WEBCAMS_KEY}"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000