#!/usr/bin/env python3

#
# Server gRPC per Hello 
#

from .HelloGrpcServer import HelloGrpcServer

import logging

def run():
    print("Starting Hello Service (with gRPC Server)")
    hello_grpc_server = HelloGrpcServer()
    hello_grpc_server.start() 
    
if __name__ == "__main__":
    logging.basicConfig()
    run()

