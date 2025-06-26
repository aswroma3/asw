#!/bin/bash

echo Creating some Kafka topics...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic asw-alpha --replication-factor 1 --partitions 4  
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic asw-beta --replication-factor 1 --partitions 3  
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic asw-gamma --replication-factor 1 --partitions 2  
