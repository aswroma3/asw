#!/usr/bin/env python3

#
# Hello REST Server 
#

import uvicorn 

import logging

def run():
    print("Running Hello Service (with REST Server)")
    uvicorn.run("hello_service.HelloRestServer:app", port=8080, host="0.0.0.0")

if __name__ == "__main__":
    logging.basicConfig()
    run()
    