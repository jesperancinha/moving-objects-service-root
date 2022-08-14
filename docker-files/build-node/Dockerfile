FROM node:18

RUN npm install -g npm

RUN npm install typescript -g

RUN npm install -g @angular/cli

RUN chown -R root:1000 /usr/local/lib/node_modules/

RUN chmod -R 777 /usr/local/lib/node_modules/

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]