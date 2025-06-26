#!/bin/bash

# delete all containers from image myhello 

IMAGENAME=myhello

docker container rm $(docker container ls -a | grep $IMAGENAME | awk '{ print $NF }')
