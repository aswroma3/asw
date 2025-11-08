#!/bin/bash

# Script per avviare Kafka con Docker Compose (v2)

echo Starting infrastructure...

docker compose up -d 

# waiting for kafka to start 
sleep 5 

echo Creating Kafka topics for the restaurant service...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic restaurant-service-event-channel --replication-factor 1 --partitions 4
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic restaurant-service-command-channel --replication-factor 1 --partitions 4

