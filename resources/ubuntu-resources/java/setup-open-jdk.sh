#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://openjdk.org/
# https://openjdk.org/projects/jdk/ 

# set up Java constants 
#OPENJDK_PACKAGE=openjdk-17-jdk
OPENJDK_PACKAGE=openjdk-21-jdk

echo "==================="
echo "installing open jdk"
echo "==================="

#apt-get update 
apt-get install -y ${OPENJDK_PACKAGE}
