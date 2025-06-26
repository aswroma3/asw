#!/bin/bash

source /home/asw/venv/bin/activate 

# mi sembra di capire che il parametro -I debba valere output_folder=source_proto_folder 
python3 -m grpc_tools.protoc -Ihello_grpc=hello_proto --python_out=. --pyi_out=. --grpc_python_out=. hello_proto/HelloService.proto
