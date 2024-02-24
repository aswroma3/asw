#!/bin/bash

docker container create --name=my-data-volume my-data-volume 
docker container start my-data-volume 

docker container create --volumes-from my-data-volume -p 8080:80 --name=myapache2 myapache
docker container start myapache2
