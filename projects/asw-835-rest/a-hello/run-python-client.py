#!/usr/bin/python3

import httpx 

base_uri = 'http://localhost:8080/hello/'
names = ['Luca', 'World', 'Paolo', 'Barbara', 'Python'] 

for name in names: 
    greeting = httpx.get(base_uri+name).text
    print(greeting)

