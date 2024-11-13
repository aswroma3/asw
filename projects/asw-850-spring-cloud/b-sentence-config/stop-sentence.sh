#!/bin/bash

# Script per terminare l'applicazione Sentence 

echo Halting SENTENCE   

pkill -f 'word.jar'
pkill -f 'sentence.jar'
pkill -f 'config-server.jar'
