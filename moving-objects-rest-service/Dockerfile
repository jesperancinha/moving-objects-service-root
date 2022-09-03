FROM openjdk:19-alpine

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apk --no-cache add curl

COPY build/libs/moving-objects-rest-service.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8082
