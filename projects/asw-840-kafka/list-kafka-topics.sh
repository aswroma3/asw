#!/bin/bash

echo Listing Kafka topics...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER  kafka-topics.sh --bootstrap-server localhost:9092 --list
