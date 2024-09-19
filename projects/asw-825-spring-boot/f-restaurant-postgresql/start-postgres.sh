#!/bin/bash

# Script per avviare Postgres con Docker Compose (v2)

echo Starting Postgres...

# docker compose up -d 
docker compose --profile debug up -d 

