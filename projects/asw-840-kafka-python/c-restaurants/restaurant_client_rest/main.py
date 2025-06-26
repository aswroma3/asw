#!/usr/bin/env python3

from .ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

import logging

@inject
def run(restaurant_client_runner = Provide[Container.restaurant_client_runner_provider]):
    print("Running Restaurant REST Client")
    restaurant_client_runner.run() 

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
    container.wire(modules=[__name__])
    run()
