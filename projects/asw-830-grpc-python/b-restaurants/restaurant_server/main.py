#!/usr/bin/env python3

#
# Restaurant gRPC Server 
#

from .ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

import logging

@inject
def run(restaurant_grpc_server = Provide[Container.restaurant_grpc_server_provider]):
    print("Starting Restaurant gRPC Server")
    restaurant_grpc_server.start() 

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
    container.wire(modules=[__name__])
    run()
