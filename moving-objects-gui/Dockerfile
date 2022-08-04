FROM nginx

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt update -y
RUN apt install nodejs npm -y
RUN npm i -g pm2

COPY entrypoint.sh ${runningFolder}

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY dist /usr/share/nginx/html

COPY metrics-server ${runningFolder}/metrics-server

RUN cd metrics-server && npm i && cd ..

RUN nginx -t

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081

EXPOSE 8082

EXPOSE 4000