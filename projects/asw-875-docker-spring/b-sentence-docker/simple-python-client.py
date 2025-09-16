import httpx 
import sys 

n = 10 
if len(sys.argv)>1: 
    n = int(sys.argv[1])
for i in range(n): 
    sentence = httpx.get('http://localhost:8080').text
    print(sentence)

