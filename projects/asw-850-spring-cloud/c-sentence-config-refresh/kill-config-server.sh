#!/bin/bash

echo Kill a running configuration server 

TOMATCH=config-server

PROCESSTOKILL=$(ps -af | grep $TOMATCH | grep -v grep | awk '{print $2}' | head -n 1)
echo "Killing process $PROCESSTOKILL"
kill -9 $PROCESSTOKILL

