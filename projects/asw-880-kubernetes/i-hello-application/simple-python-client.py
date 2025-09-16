import httpx 
import sys 

n = 10 
if len(sys.argv)>1: 
    n = int(sys.argv[1])
for i in range(n): 
    greeting = httpx.get('http://kube-node', headers={'Host': 'hello.aswroma3.it'}).text
    print(greeting)

