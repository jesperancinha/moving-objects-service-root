#!/bin/bash
echo '╔════════════════════════════════════════════════╗'
echo '║------------------------------------------------║'
echo -e '║--- \033[1;31mRun the export command shown at the end\033[0m ----║'
echo '║------------------------------------------------║'
echo '╚════════════════════════════════════════════════╝'
echo '- Input your ISSUER=;CLIENT_ID=;CLIENT_SECRET=;'
echo '- This is in the same format as you would have on your IDE'
printf "$ >"
read -r csvString
IFS=';'
values=$csvString
exportString=''
for x in $values
do
   exportString="$exportString $x"
done

exportString=$(echo "$exportString" | xargs)
echo -e "\033[1;32mexport $exportString"
echo -e '\033[1;32mDone!\033[0m'
