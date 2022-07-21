FROM node:18

RUN cd moving-objects-gui && npm run build
