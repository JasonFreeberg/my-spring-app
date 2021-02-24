#!/bin/sh

GROUP=freebergDemo
WEBAPP=freeberg-windows
ACCOUNT=xzopublishingaccount
CONTAINER=deploymentcontainer

az storage account create   -n $ACCOUNT   -g $GROUP -l westus
az storage container create -n $CONTAINER --account-name $ACCOUNT
az storage blob upload      -f app.zip    --account-name $ACCOUNT -c $CONTAINER -n $ACCOUNT

end=`date -u -d "30 minutes" '+%Y-%m-%dT%H:%MZ'`
ZIP_URL=$(az storage blob generate-sas --full-uri --permissions r --expiry $end --account-name $ACCOUNT -c $CONTAINER -n $ACCOUNT | xargs)

az webapp deploy --name $WEBAPP --resource-group $GROUP --type zip --src-url $ZIP_URL
            