FROM nginx

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY entrypoint.sh ${runningFolder}

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY dist /usr/share/nginx/html

COPY metrics-server/server.js ${runningFolder}

COPY metrics-server/package.json ${runningFolder}

ARG AIRPORTS_KEY

ARG WEBCAMS_KEY

ENV AIRPORTS_KEY=${AIRPORTS_KEY}

ENV WEBCAMS_KEY=${WEBCAMS_KEY}

RUN nginx -t

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000