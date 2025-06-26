#!/usr/bin/env python3

#
# Restaurant Command Publisher 
#

from .ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

import logging

@inject
def run(restaurant_command_publisher_runner = Provide[Container.restaurant_command_publisher_runner_provider]):
    print("Running Restaurant Command Publisher")
    restaurant_command_publisher_runner.run()

if __name__ == "__main__":
    logging.basicConfig()
    container = Container()
    container.wire(modules=[__name__])
    run()
    