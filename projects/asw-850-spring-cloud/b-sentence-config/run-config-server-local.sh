#!/bin/bash

echo Starting Config Server

java -Xms64m -Xmx128m -jar -Dspring.profiles.active=native config-server/build/libs/config.jar &
# java -Xms64m -Xmx128m -jar config-server/build/libs/config.jar &

