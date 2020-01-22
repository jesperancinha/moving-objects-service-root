FROM jesperancinha/java-exercise-docker:0.0.3

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY international-airports-rest/international-airports-rest-service/build/libs/international-airports-rest-service-1.0.0.jar ${runningFolder}

COPY international-airports-sst/international-airports-sst-live/build/libs/international-airports-sst-live-1.0.0.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh","${AIRPORTS_KEY}", "${WEBCAMS_KEY}"]

EXPOSE 8081

EXPOSE 8082