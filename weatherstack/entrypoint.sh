#!/bin/sh
set -e

mvn verify -Denv=stag -Dplugin=sendinblue -Dcucumber.filter.tags="@Sendinblue"
#mvn test verify -Denv=$1 -Dplugin=$2 -Dcucumber.filter.tags="$3"

echo "Displaying reports of $2 for env $1" 
# cat /home/node/app/target/cucumber/cucumberReport.json

