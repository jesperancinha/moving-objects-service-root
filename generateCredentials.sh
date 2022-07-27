#!/bin/bash

cd moving-objects-jwt-service/src/main/resources || exit

openssl genrsa -out baseKey.pem

openssl pkcs8 -topk8 -inform PEM -in baseKey.pem -out objects.private.key -nocrypt

openssl rsa -in baseKey.pem -pubout -outform PEM -out objects.public.key
