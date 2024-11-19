#!/bin/bash

# docker login -u aswroma3 
docker build --rm -t whalesay -t aswroma3/whalesay .
docker push aswroma3/whalesay
