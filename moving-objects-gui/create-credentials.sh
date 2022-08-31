#!/bin/bash
sed -e "s/\${CLIENT_ID}/${CLIENT_ID}/" \
    -e "s+\${ISSUER}+${ISSUER}+" \
    -e "s+\${REDIRECT_LOGOUT}+http://localhost:""${REDIRECT_PORT:-8080}""/signout+" \
    -e "s+\${REDIRECT_SERVICE_URL}+http://localhost:""${REDIRECT_PORT:-8080}""/aggregator/webcams/camera/GAR+" \
    -e "s+\${REDIRECT_URL}+http://localhost:""${REDIRECT_PORT:-8080}""/callback+" \
    -e "s+\${CLIENT_SECRET}+${CLIENT_SECRET}+" okta.config.template.ts \
    > src/environments/okta.config.ts
