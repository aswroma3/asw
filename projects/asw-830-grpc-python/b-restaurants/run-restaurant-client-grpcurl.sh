#!/bin/bash

PROTO_FOLDER=restaurant_service_api_proto

grpcurl -plaintext -proto ${PROTO_FOLDER}/RestaurantService.proto localhost:50051 RestaurantService/getAllRestaurants
