FROM node:18

RUN npm install -g npm

RUN npm install typescript -g

RUN npm install -g @angular/cli

RUN chown -R root:1000 /usr/local/lib/node_modules/

RUN chmod -R 777 /usr/local/lib/node_modules/

RUN mkdir -p /opt/moving-objects-gui/node_modules/@angular-builders/custom-webpack/node_modules/babel-loader

RUN chown -R root:1000 /opt/moving-objects-gui

RUN chmod -R 777 /opt/moving-objects-gui

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY entrypoint.sh ${runningFolder}

ENTRYPOINT ["entrypoint.sh"]