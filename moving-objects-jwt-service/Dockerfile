FROM openjdk:20-slim-buster

ENV runningFolder /usr/local/bin

WORKDIR ${runningFolder}

COPY build/libs/moving-objects-jwt-service.jar ${runningFolder}/

RUN mkdir ${runningFolder}/cameras

COPY cameras ${runningFolder}/cameras/

COPY entrypoint.sh ${runningFolder}

RUN apt-get clean

RUN apt-get update

RUN apt-get install curl -y

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081
