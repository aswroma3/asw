import httpx

class HelloServiceRestClient: 
    def __init__(self): 
        pass
    def sayHello(self, name: str): 
        return httpx.get("http://localhost:8080/hello/{}".format(name)).text
