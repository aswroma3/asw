#!/bin/bash

docker compose -f docker-compose.yml up --no-build --scale subject=1 --scale verb=1 --scale object=1 >/dev/null &






