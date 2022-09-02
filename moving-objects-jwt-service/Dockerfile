FROM openjdk:17-alpine

ENV runningFolder /usr/local/bin

WORKDIR ${runningFolder}

RUN apk --no-cache add curl

COPY build/libs/moving-objects-jwt-service.jar ${runningFolder}/

RUN mkdir ${runningFolder}/cameras

COPY cameras ${runningFolder}/cameras/

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081
