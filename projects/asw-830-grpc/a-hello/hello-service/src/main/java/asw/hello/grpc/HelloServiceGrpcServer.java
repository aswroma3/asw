package asw.hello.grpc;

import asw.hello.domain.HelloService;
import asw.hello.grpc.proto.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;
import java.util.*; 
import java.util.stream.*; 

//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Component
public class HelloServiceGrpcServer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
    private HelloService helloService;

	@Value("${asw.helloservice.grpc.port}")
    private int port;
	
    private Server server;
	
    @PostConstruct
    public void start() throws IOException {
        this.server = ServerBuilder.forPort(port)
                .addService(new HelloServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            logger.info("*** server shut down");
        }
    }

    private class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            String name = request.getName();
			logger.info("gRPC server: sayHello(" + name + ")");
			String greeting = helloService.sayHello(name); 
			logger.info("gRPC server: sayHello(" + name + "): " + greeting);
            HelloReply reply = HelloReply.newBuilder()
                    .setGreeting(greeting)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

    }

}
