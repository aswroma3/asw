#!/bin/bash

PROTO_FOLDER=restaurant-service-api-grpc/src/main/proto

grpcurl -plaintext -proto ${PROTO_FOLDER}/RestaurantService.proto localhost:50051 RestaurantService/getAllRestaurants
