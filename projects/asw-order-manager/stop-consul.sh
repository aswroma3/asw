#!/bin/bash

echo Halting Consul  

docker stop asw-consul 
docker rm asw-consul 
