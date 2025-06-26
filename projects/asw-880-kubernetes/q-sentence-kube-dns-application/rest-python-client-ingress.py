import httpx 

import threading
import time

import sys 

def tre_decimali(x): 
	return f"{x:.3f}"

def get_sentence(i, uri, cluster, port, start):
    mystart = time.time()
    sentence = httpx.get('http://'+cluster, headers={'Host': uri}).text
    myend = time.time()
    print(i, ' start ---> ', tre_decimali(mystart-start), ' duration ---> ', tre_decimali(myend-mystart), ' ', sentence)

# numero di chiamate 
n = int(sys.argv[1])
# intervallo tra una chiamata e la successiva (ms)
delay = int(sys.argv[2])
uri = sys.argv[3]
cluster = sys.argv[4]
port = sys.argv[5]
start = time.time()
for i in range(n): 
    t1 = threading.Thread(target=get_sentence, args=[i, uri, cluster, port, start])
    t1.start()
    time.sleep(delay/1000)

