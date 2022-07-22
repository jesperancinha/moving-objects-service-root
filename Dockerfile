FROM node:18

RUN npm install -g npm

RUN npm install typescript -g

RUN npm install -g @angular/cli

RUN npm install --save-dev @angular-devkit/build-angular
