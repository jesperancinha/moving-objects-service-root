FROM openjdk:20-slim-buster

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY build/libs/moving-objects-rest-service.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8082
