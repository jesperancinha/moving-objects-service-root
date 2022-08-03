FROM openjdk:20-slim-buster

ENV runningFolder /usr/local/bin

WORKDIR ${runningFolder}

COPY build/libs/moving-objects-jwt-service.jar ${runningFolder}/

RUN mkdir ${runningFolder}/cameras

COPY cameras ${runningFolder}/cameras/

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081
