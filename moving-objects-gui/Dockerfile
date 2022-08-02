FROM nginx

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY entrypoint.sh ${runningFolder}

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY dist /usr/share/nginx/html

RUN mkdir ${runningFolder}/metrics-server

COPY metrics-server ${runningFolder}/metrics-server/

RUN nginx -t

RUN apt update -y
RUN apt install nodejs npm -y

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000