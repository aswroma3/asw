#!/usr/bin/python3

import httpx 
import sys 

n = 10 
if len(sys.argv)>1: 
    n = int(sys.argv[1])
for i in range(n): 
    sentence = httpx.get('http://kube-node', headers={'Host': 'sentence.aswroma3.it'}).text
    print(sentence)

