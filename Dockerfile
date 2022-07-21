FROM node:18

RUN cd /root/moving-objects-gui && npm run build
