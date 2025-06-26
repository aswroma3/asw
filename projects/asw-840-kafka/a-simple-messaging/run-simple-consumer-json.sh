#!/bin/bash

SPRING_KAFKA_CONSUMER_VALUE_DESERIALIZER=org.springframework.kafka.support.serializer.JsonDeserializer gradle simple-consumer:bootRun &
