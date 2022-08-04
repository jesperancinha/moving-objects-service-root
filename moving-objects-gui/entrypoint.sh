#!/usr/bin/env bash
pm2 start metrics-server/src/server.js
nginx
tail -f /dev/null