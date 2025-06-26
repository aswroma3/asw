#!/bin/bash

docker stop $(docker ps -a | grep sentence | awk '{print $1}') 
docker stop consul 

docker rm $(docker ps -a | grep sentence | awk '{print $1}')
docker rm consul 

docker network rm sentence-net 
