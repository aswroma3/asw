#!/bin/bash

echo Halting RabbitMQ  

docker stop asw-rabbit 
docker rm asw-rabbit 
