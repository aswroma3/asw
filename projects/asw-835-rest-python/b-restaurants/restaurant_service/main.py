#!/usr/bin/env python3

#
# Restaurant REST Server 
#

from .ioc_container import Container 

#from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

import uvicorn 

import logging

def run():
    print("Running Restaurant Service (with REST Server)")
    uvicorn.run("restaurant_service.rest_server.RestaurantRestServer:app", port=8080, host="0.0.0.0")

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
#    container.wire(modules=[__name__, "restaurant_service.rest_server.RestaurantRestServer"])
    container.wire(modules=["restaurant_service.rest_server.RestaurantRestServer"])
    run()
