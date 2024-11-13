import httpx 

import threading
import time

import sys 

def tre_decimali(x): 
	return f"{x:.3f}"

def get_sentence(i, start):
    mystart = time.time()
    print(i, 'start ---> ', tre_decimali(mystart-start))
    print(i, ' ', httpx.get('http://localhost:8080').text, ' duration ---> ', tre_decimali(time.time()-mystart))

# numero di chiamate 
n = int(sys.argv[1])
# intervallo tra una chiamata e la successiva (ms)
delay = int(sys.argv[2])

start = time.time()
for i in range(n): 
    t1 = threading.Thread(target=get_sentence, args=[i, start])
    t1.start()
    time.sleep(delay/1000)

