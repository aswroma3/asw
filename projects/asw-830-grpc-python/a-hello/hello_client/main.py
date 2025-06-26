#!/usr/bin/env python3

#
# Client del servizio Hello 
#

from .HelloClientRunner import HelloClientRunner

import logging

def run():
    print("Running Hello gRPC Client")
    HelloClientRunner.run()

if __name__ == "__main__":
    logging.basicConfig()
    run()