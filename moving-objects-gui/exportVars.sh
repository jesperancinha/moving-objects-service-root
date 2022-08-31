#!/bin/bash
echo '╔════════════════════════════════════════════════╗'
echo '║------------------------------------------------║'
echo -e '║---- \033[1;31mRun this script with . ./exportVars.sh\033[0m ----║'
echo '║------------------------------------------------║'
echo -e '║-- \033[1;31mOr run the export command shown at the end\033[0m --║'
echo '║------------------------------------------------║'
echo '╚════════════════════════════════════════════════╝'
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
echo -e '\033[1;34mExport data is:\033[0m'
echo -e "* ISSUER=\033[1;34m$issuer\033[0m"
echo -e "* CLIENT_ID=\033[1;34m$clientId\033[0m"
echo -e "* CLIENT_SECRET=\033[1;34m$clientSecret\033[0m"
echo -e "\033[1;32mexport ISSUER=$issuer CLIENT_ID=$clientId CLIENT_SECRET=$clientSecret"
echo -e '\033[1;32mDone!\033[0m'
