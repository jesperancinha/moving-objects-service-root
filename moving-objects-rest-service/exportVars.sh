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
if [ -n "$ISSUER" ]; then
    echo "(""$ISSUER"" as default)"
fi
printf "$ >"
read -r issuer
export ISSUER=${issuer:-${ISSUER}}
echo '- Set your CLIENT_ID'
if [ -n "$CLIENT_ID" ]; then
    echo "(""$CLIENT_ID"" as default)"
fi
printf "$ >"
read -r clientId
export CLIENT_ID=${clientId:-${CLIENT_ID}}
echo '- Set your CLIENT_SECRET'
if [ -n "$CLIENT_SECRET" ]; then
    echo "(""$CLIENT_SECRET"" as default)"
fi
printf "$ >"
read -r clientSecret
export CLIENT_SECRET=${clientSecret:-${CLIENT_SECRET}}
export REDIRECT_PORT=${redirectPort:-${REDIRECT_PORT:-8080}}
echo '- Set your REDIRECT_PORT (the REDIRECT_URL is always localhost:REDIRECT_PORT for this project)'
if [ -n "$REDIRECT_PORT" ]; then
    echo "(""$REDIRECT_PORT"" as default)"
fi
printf "$ >"
read -r redirectPort
export REDIRECT_PORT=${redirectPort:-8080}
echo -e '\033[1;34mExport data is:\033[0m'
echo -e "* ISSUER=\033[1;34m${ISSUER}\033[0m"
echo -e "* CLIENT_ID=\033[1;34m${CLIENT_ID}\033[0m"
echo -e "* CLIENT_SECRET=\033[1;34m${CLIENT_SECRET}\033[0m"
echo -e "* REDIRECT_PORT=\033[1;34m${REDIRECT_PORT}\033[0m"
echo -e "\033[1;32mexport ISSUER=${ISSUER} CLIENT_ID=${CLIENT_ID} CLIENT_SECRET=${CLIENT_SECRET} REDIRECT_PORT=${REDIRECT_PORT}"
echo -e "\033[1;32mFor your IDE (Intellij, Eclipse, VisualCode...) CSV -> ISSUER=${ISSUER};CLIENT_ID=${CLIENT_ID};CLIENT_SECRET=${CLIENT_SECRET};REDIRECT_PORT=${REDIRECT_PORT}"
echo -e '\033[1;35mDone!\033[0m'
