#
# Implementazione del servizio Hello 
#
class HelloService: 
    def sayHello(self, name: str): 
        return "Hello, {}!".format(name)

hello_service = HelloService()
