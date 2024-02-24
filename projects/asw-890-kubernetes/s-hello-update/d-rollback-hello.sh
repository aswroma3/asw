#!/bin/bash

echo 'Rollbacking hello (to previous version)' 

kubectl rollout undo deployment hello-update -n hello-update

kubectl rollout status deployment hello-update -n hello-update


