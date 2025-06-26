#!/bin/bash

docker image rm $(docker image ls | grep sentence | grep compose | awk '{print $3}')




