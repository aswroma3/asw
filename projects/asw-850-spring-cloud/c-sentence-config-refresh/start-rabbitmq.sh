#!/bin/bash

echo Starting RabbitMQ in a Docker Container 

docker run -d --hostname localhost --name asw-rabbit --publish 5672:5672 rabbitmq:4
