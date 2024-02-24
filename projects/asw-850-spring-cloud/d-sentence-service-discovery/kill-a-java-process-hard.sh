#!/bin/bash

echo Kill -9 a java process matching part of its command line

TOMATCH=$1

if [ -n "$TOMATCH" ]
then
	PROCESSTOKILL=$(ps -af | grep $TOMATCH | grep -v grep | awk '{print $2}' | head -n 1)
	echo "Killing process $PROCESSTOKILL"
	kill -9 $PROCESSTOKILL
else
    echo "Usage: $0 string-to-match-in-command-line"
fi
