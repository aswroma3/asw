#
# Implementazione del servizio Hello 
#
class HelloService: 
    def __init__(self): 
        pass 
    def sayHello(self, name: str): 
        return "Hello, {}!".format(name)

hello_service = HelloService()