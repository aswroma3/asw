#!/usr/bin/env python3

#
# Restaurant Server - REST e Kafka  
#

from .ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

import uvicorn 

import logging

@inject
def run(restaurant_event_publisher = Provide[Container.restaurant_event_publisher_provider], 
        restaurant_service = Provide[Container.restaurant_service_provider], 
        restaurant_command_listener = Provide[Container.restaurant_command_listener_provider], 
        simple_message_consumer = Provide[Container.simple_message_consumer_provider]):
    print("Running Restaurant REST Server")
    uvicorn.run("restaurant_server.rest_server.RestaurantRestServer:app", port=8080, host="0.0.0.0")

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
#    container.wire(modules=[__name__, "restaurant_server.rest_server.RestaurantRestServer"])
    container.wire(modules=[__name__, "restaurant_server.rest_server.RestaurantRestServer"])
    run()
