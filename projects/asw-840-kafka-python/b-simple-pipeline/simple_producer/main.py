#!/usr/bin/env python3

#
# Simple Producer 
#

from .SimpleProducerRunner import SimpleProducerRunner

import logging

def run():
    print("Running Simple Producer")
    SimpleProducerRunner.run()

if __name__ == "__main__":
    logging.basicConfig()
    run()
    
