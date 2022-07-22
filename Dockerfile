FROM node:18

RUN npm install -g npm

RUN npm install typescript -g

RUN npm install -g @angular/cli

RUN cd moving-objects-cams-gui && mkdir node_modules
