#!/bin/bash

source /home/asw/venv/bin/activate 

# mi sembra di capire che il parametro -I debba valere output_folder=source_proto_folder 
python3 -m grpc_tools.protoc -Irestaurant_service_api_grpc=restaurant_service_api_proto --python_out=. --pyi_out=. --grpc_python_out=. restaurant_service_api_proto/RestaurantService.proto
