#!/bin/bash

source "/home/asw/resources/common.sh"

# set up Grpcurl constants 
# see https://github.com/fullstorydev/grpcurl 
# https://github.com/fullstorydev/grpcurl/releases/download/v1.9.2/grpcurl_1.9.2_linux_amd64.deb

#GRPCURL_VERSION=1.9.2
GRPCURL_VERSION=1.9.3

GRPCURL_ARCHIVE=grpcurl_${GRPCURL_VERSION}_linux_amd64.deb
GET_GRPCURL_URL=https://github.com/fullstorydev/grpcurl/releases/download/v${GRPCURL_VERSION}

echo "=================="
echo "installing grpcurl"
echo "=================="

# download grpcurl 
if ! downloadExists $GRPCURL_ARCHIVE; then
	wget -q -P ${ASW_DOWNLOADS} ${GET_GRPCURL_URL}/${GRPCURL_ARCHIVE} 
fi

# install grpcurl 
FILE=${ASW_DOWNLOADS}/${GRPCURL_ARCHIVE}
dpkg -i $FILE 
