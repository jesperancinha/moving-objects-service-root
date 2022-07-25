FROM openjdk:20-slim-buster

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY build/libs/moving-objects-rest-service.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ARG AIRPORTS_KEY

ARG WEBCAMS_KEY

ENV AIRPORTS_KEY=${AIRPORTS_KEY}

ENV WEBCAMS_KEY=${WEBCAMS_KEY}

ENTRYPOINT ["entrypoint.sh","${AIRPORTS_KEY}", "${WEBCAMS_KEY}"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000