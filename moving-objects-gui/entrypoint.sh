#!/usr/bin/env bash
cd metrics-server && npm i && cd ..
npm i -g pm2
pm2 start metrics-server/src/server.js
nginx
tail -f /dev/null