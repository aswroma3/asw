#
# Client gRPC per il servizio Hello 
#

import grpc
import hello_grpc.HelloService_pb2 as HelloService_pb2
import hello_grpc.HelloService_pb2_grpc as HelloService_pb2_grpc

# parameters 

hello_grpc_port = 50051 

class HelloServiceGrpcClient: 
    def __init__(self): 
        channel = grpc.insecure_channel("localhost:{}".format(hello_grpc_port))
        self.stub = HelloService_pb2_grpc.HelloServiceStub(channel)
    def sayHello(self, name: str): 
        response = self.stub.sayHello(HelloService_pb2.HelloRequest(name=name))
        return response.greeting
