#!/bin/bash

echo Starting Consul in a Docker Container 

docker run -d --hostname localhost --name asw-consul --publish 8500:8500 hashicorp/consul

