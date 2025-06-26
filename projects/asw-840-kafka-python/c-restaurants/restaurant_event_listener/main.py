#!/usr/bin/env python3

#
# Restaurant Event Listener 
#

from .ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

#from restaurant_event_listener.domain.RestaurantEventListener import RestaurantEventListener 

import logging

@inject
def run(restaurant_event_listener = Provide[Container.restaurant_event_listener_provider], simple_message_consumer = Provide[Container.simple_message_consumer_provider]):
    print("Running Restaurant Event Listener")
    # restaurant_event_listener.start() 

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
#    container.wire(modules=[__name__, "restaurant_server.rest_server.RestaurantRestServer"])
    container.wire(modules=[__name__])
    run()
