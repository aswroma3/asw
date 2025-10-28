#
# Server gRPC per Hello 
#

from .HelloService import hello_service 

import grpc
import hello_grpc.HelloService_pb2 as HelloService_pb2
import hello_grpc.HelloService_pb2_grpc as HelloService_pb2_grpc

from concurrent import futures

# parameters 

hello_grpc_port = 50051 

class HelloServiceServicer(HelloService_pb2_grpc.HelloServiceServicer):
    def __init__(self): 
        pass 
    
    def sayHello(self, request, context):
        name = request.name
        greeting = hello_service.sayHello(name)
        return HelloService_pb2.HelloReply(greeting=greeting)

class HelloGrpcServer: 
    def __init__(self): 
        pass 

    def start(self):
        server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
        HelloService_pb2_grpc.add_HelloServiceServicer_to_server(
            HelloServiceServicer(), server
        )
        server.add_insecure_port("[::]:{}".format(hello_grpc_port))
        server.start()
        server.wait_for_termination()
