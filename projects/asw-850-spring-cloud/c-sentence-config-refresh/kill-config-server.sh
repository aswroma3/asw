#!/bin/bash

echo Kill a running configuration server 

echo "Halting process $PROCESSTOKILL"

pkill -f 'config-server.jar'

