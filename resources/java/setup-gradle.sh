#!/bin/bash

source "/home/asw/resources/common.sh"

# set up Gradle constants 
# https://services.gradle.org/distributions/gradle-8.6-bin.zip

#GRADLE_VERSION=8.6
#GRADLE_VERSION=8.10
#GRADLE_VERSION=8.10.1
GRADLE_VERSION=8.11

GRADLE_ARCHIVE=gradle-${GRADLE_VERSION}-bin.zip
GET_GRADLE_URL=https://services.gradle.org/distributions
GRADLE_PATH=/usr/local/gradle-${GRADLE_VERSION} 
# e.g. /usr/local/gradle-6.8.3

function installLocalGradle {
	echo "================="
	echo "installing gradle"
	echo "================="
	FILE=${ASW_DOWNLOADS}/$GRADLE_ARCHIVE
	unzip -q $FILE -d /usr/local
}

function installRemoteGradle {
	echo "=================="
	echo "downloading gradle"
	echo "=================="
	wget -q -P ${ASW_DOWNLOADS} ${GET_GRADLE_URL}/${GRADLE_ARCHIVE} 
	installLocalGradle
}

function setupGradle {
	echo "setting up gradle"
	if fileExists $GRADLE_PATH; then
		ln -s $GRADLE_PATH /usr/local/gradle
	fi
}

function setupEnvVars {
	echo "creating gradle environment variables"
	echo export GRADLE_HOME=/usr/local/gradle >> /etc/profile.d/gradle.sh
	echo export PATH=\${PATH}:\${GRADLE_HOME}/bin >> /etc/profile.d/gradle.sh
}

function installGradle {
	if downloadExists $GRADLE_ARCHIVE; then
		installLocalGradle
	else
		installRemoteGradle
	fi
}

function installPrerequisites {
	echo "installing unzip"
	apt-get install unzip 
}

echo "setup gradle"
installPrerequisites
installGradle
setupGradle
setupEnvVars
