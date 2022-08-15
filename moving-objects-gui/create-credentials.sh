#!/bin/bash
sed -e "s/\${CLIENT_ID}/${CLIENT_ID}/" \
    -e "s+\${ISSUER}+${ISSUER}+" \
    -e "s+\${REDIRECT_URL}+${REDIRECT_URL}+" \
    -e "s+\${CLIENT_SECRET}+${CLIENT_SECRET}+" okta.config.template.ts \
    > src/environments/okta.config.ts
