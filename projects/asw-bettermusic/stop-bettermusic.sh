#!/bin/bash

echo Halting BETTERMUSIC

pkill -f 'recensioni-seguite.jar'
pkill -f 'recensioni.jar'
pkill -f 'connessioni.jar'
pkill -f 'album.jar'
pkill -f 'api-gateway.jar'

