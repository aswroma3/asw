#!/bin/bash

# Script per terminare l'applicazione Sentence 

echo Halting SENTENCE   

pkill -f 'word.jar'
pkill -f 'sentence.jar'
pkill -f 'sentence-async.jar'
pkill -f 'apigateway.jar'
