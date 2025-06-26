#!/usr/bin/env python3

#
# Simple Kafka Consumer 
#

from .SimpleConsumerService import SimpleConsumerService 

import logging

def run():
    print("Running Simple Consumer")
    simple_consumer_service = SimpleConsumerService()
    # simple_consumer_service.start() 

if __name__ == "__main__":
    logging.basicConfig()
    run()