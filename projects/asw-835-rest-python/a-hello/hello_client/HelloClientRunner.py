#
# Client del servizio Hello 
#

from .HelloServiceClient import hello_service_client 

class HelloClientRunner: 
    def run():
        print( hello_service_client.sayHello('World') )
        print( hello_service_client.sayHello('Luca') )
