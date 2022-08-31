#!/bin/bash
echo '╔════════════════════════════════════════════════╗'
echo '║------------------------------------------------║'
echo -e '║---- \033[1;31mRun this script with . ./exportVars.sh\033[0m ----║'
echo '║------------------------------------------------║'
echo -e '║-- \033[1;31mOr run the export command shown at the end\033[0m --║'
echo '║------------------------------------------------║'
echo '╚════════════════════════════════════════════════╝'
echo 'Log into your https://developer.okta.com/ Dev account  and fill in the following fields'
echo 'Be sure to have your user available with a user/password combination in Okta'
echo 'At the end of this form, export command manually in the command line in order to start the start-demo-secure script'
echo '- Set your ISSUER (https://...)'
printf "$ >"
read -r issuer
export ISSUER=$issuer
echo '- Set your CLIENT_ID'
printf "$ >"
read -r clientId
export CLIENT_ID=$clientId
echo '- Set your CLIENT_SECRET'
printf "$ >"
read -r clientSecret
export CLIENT_SECRET=$clientSecret
echo '- Set your REDIRECT_PORT (the REDIRECT_URL is always localhost:REDIRECT_PORT for this project)'
echo '- (8080 as default)'
printf "$ >"
read -r redirectPort
export REDIRECT_PORT=${redirectPort:-8080}
echo -e '\033[1;34mExport data is:\033[0m'
echo -e "* ISSUER=\033[1;34m$issuer\033[0m"
echo -e "* CLIENT_ID=\033[1;34m$clientId\033[0m"
echo -e "* CLIENT_SECRET=\033[1;34m$clientSecret\033[0m"
echo -e "* REDIRECT_PORT=\033[1;34m${REDIRECT_PORT}\033[0m"
echo -e "\033[1;32mexport ISSUER=$issuer CLIENT_ID=$clientId CLIENT_SECRET=$clientSecret REDIRECT_PORT=${REDIRECT_PORT}"
echo -e '\033[1;32mDone!\033[0m'
