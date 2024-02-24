#!/bin/bash

docker compose -f docker-compose.yml up --no-build --scale subject=2 --scale verb=2 --scale object=2 >/dev/null &






