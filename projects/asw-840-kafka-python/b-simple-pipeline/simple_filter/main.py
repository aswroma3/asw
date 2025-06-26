#!/usr/bin/env python3

#
# Simple Kafka Filter 
#

from .SimpleFilterService import SimpleFilterService 

import logging

def run():
    print("Running Simple Filter")
    simple_filter_service = SimpleFilterService()
    # simple_filter_service.start()

if __name__ == "__main__":
    logging.basicConfig()
    run()
    
