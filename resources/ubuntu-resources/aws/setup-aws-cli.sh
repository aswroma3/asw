#!/bin/bash

# see https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html 

source "/home/asw/resources/common.sh"

AWSCLI_ARCHIVE=awscli-exe-linux-x86_64.zip
GET_AWSCLI_URL=https://awscli.amazonaws.com

echo "=================="
echo "installing aws cli"
echo "=================="

# download aws cli 
if ! downloadExists $AWSCLI_ARCHIVE; then
	wget -q -P ${ASW_DOWNLOADS} ${GET_AWSCLI_URL}/${AWSCLI_ARCHIVE} 
fi

# install aws cli 
FILE=${ASW_DOWNLOADS}/$AWSCLI_ARCHIVE
unzip -d /tmp $FILE 
/tmp/aws/install

