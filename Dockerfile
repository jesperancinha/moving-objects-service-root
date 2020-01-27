FROM jesperancinha/je-all-build-jdk-12:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY international-airports-rest/international-airports-rest-service/build/libs/international-airports-rest-service-1.0.0.jar ${runningFolder}

COPY international-airports-sst/international-airports-sst-live/build/libs/international-airports-sst-live-1.0.0.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

COPY docker-files/default.conf /etc/nginx/conf.d/default.conf

COPY docker-files/nginx.conf /etc/nginx/nginx.conf

COPY international-airports-gui/dist ${runningFolder}/dist

COPY international-airports-gui/server.js ${runningFolder}

COPY docker-files/package.json ${runningFolder}

RUN yarn install

ARG AIRPORTS_KEY

ARG WEBCAMS_KEY

ENV AIRPORTS_KEY=${AIRPORTS_KEY}

ENV WEBCAMS_KEY=${WEBCAMS_KEY}

RUN nginx -t

ENTRYPOINT ["entrypoint.sh","${AIRPORTS_KEY}", "${WEBCAMS_KEY}"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000