#!/bin/bash

echo Halting RABBIT MQ  

docker stop asw-rabbit 
docker rm asw-rabbit 
