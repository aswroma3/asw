#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://openjdk.org/
# https://openjdk.org/projects/jdk/ 

# set up Java constants 
#OPENJDK_PACKAGE=openjdk-17-jdk
OPENJDK_PACKAGE=java-21-openjdk-devel

echo "==================="
echo "installing open jdk"
echo "==================="

yum -y install ${OPENJDK_PACKAGE}
