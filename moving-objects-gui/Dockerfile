FROM node:18
RUN npm i -g pm2
ENV runningFolder /usr/local/bin/
WORKDIR ${runningFolder}
COPY metrics-server ${runningFolder}/metrics-server
RUN cd metrics-server && npm i && cd ..

FROM nginx
COPY --from=0 /usr/local/bin /usr/local/bin
COPY --from=0 /usr/local/lib/node_modules /usr/local/lib/node_modules
ENV runningFolder /usr/local/bin/
WORKDIR ${runningFolder}
COPY entrypoint.sh ${runningFolder}
COPY default.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf
COPY dist /usr/share/nginx/html
ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8081 8082 4000
