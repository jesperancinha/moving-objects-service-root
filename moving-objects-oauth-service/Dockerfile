FROM openjdk:17-slim-buster

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY build/libs/moving-objects-oauth-service.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000