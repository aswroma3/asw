#!/bin/bash

source "/home/asw/resources/common.sh"

# set up Maven constants 
# MAVEN_VERSION=3.9.4
#MAVEN_VERSION=3.9.6
MAVEN_VERSION=3.9.9

MAVEN_ARCHIVE=apache-maven-${MAVEN_VERSION}-bin.zip
# e.g., apache-maven-3.8.1-bin.zip
# APACHE_MIRROR=http://apache.panu.it
# APACHE_MIRROR=http://it.apache.contactlab.it
APACHE_MIRROR=http://archive.apache.org
GET_MAVEN_URL=${APACHE_MIRROR}/dist/maven/maven-3/${MAVEN_VERSION}/binaries 
MAVEN_PATH=/usr/local/apache-maven-${MAVEN_VERSION} 
# e.g. /usr/local/apache-maven-3.8.1

function installLocalMaven {
	echo "================"
	echo "installing maven"
	echo "================"
	FILE=${ASW_DOWNLOADS}/$MAVEN_ARCHIVE
	unzip -q $FILE -d /usr/local
}

function installRemoteMaven {
	echo "=================="
	echo "downloading maven"
	echo "=================="
	wget -q -P ${ASW_DOWNLOADS} ${GET_MAVEN_URL}/${MAVEN_ARCHIVE} 
	installLocalMaven
}

function setupMaven {
	echo "setting up maven"
	if fileExists $MAVEN_PATH; then
		ln -s $MAVEN_PATH /usr/local/apache-maven
	fi
}

function setupEnvVars {
	echo "creating maven environment variables"
	echo export MAVEN_HOME=/usr/local/apache-maven >> /etc/profile.d/apache-maven.sh
	echo export PATH=\${PATH}:\${MAVEN_HOME}/bin >> /etc/profile.d/apache-maven.sh
}

function installMaven {
	if downloadExists $MAVEN_ARCHIVE; then
		installLocalMaven
	else
		installRemoteMaven
	fi
}

function installPrerequisites {
	echo "installing unzip"
	apt-get install unzip 
}

echo "setup maven"
installPrerequisites
installMaven
setupMaven
setupEnvVars
