#!/bin/bash

docker container stop myapache2 
docker container rm myapache2 

docker container stop my-data-volume 
docker container rm -v my-data-volume 
