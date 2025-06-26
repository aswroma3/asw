#!/bin/bash

source "/home/asw/resources/common.sh"

# set up Gradle constants 
# https://services.gradle.org/distributions/gradle-8.6-bin.zip

#GRADLE_VERSION=8.11.1
#GRADLE_VERSION=8.12.1
#GRADLE_VERSION=8.13
GRADLE_VERSION=8.14.2

GRADLE_ARCHIVE=gradle-${GRADLE_VERSION}-bin.zip
GET_GRADLE_URL=https://services.gradle.org/distributions
GRADLE_PATH=/usr/local/gradle-${GRADLE_VERSION} 
# e.g. /usr/local/gradle-6.8.3

echo "================="
echo "installing gradle"
echo "================="

# download gradle 
if ! downloadExists $GRADLE_ARCHIVE; then
	wget -q -P ${ASW_DOWNLOADS} ${GET_GRADLE_URL}/${GRADLE_ARCHIVE} 
fi

# install gradle 
FILE=${ASW_DOWNLOADS}/$GRADLE_ARCHIVE
unzip -q $FILE -d /usr/local

# setup gradle 
if fileExists $GRADLE_PATH; then
	ln -s $GRADLE_PATH /usr/local/gradle
fi

# setup env variables 
echo export GRADLE_HOME=/usr/local/gradle >> /etc/profile.d/gradle.sh
echo export PATH=\${PATH}:\${GRADLE_HOME}/bin >> /etc/profile.d/gradle.sh
