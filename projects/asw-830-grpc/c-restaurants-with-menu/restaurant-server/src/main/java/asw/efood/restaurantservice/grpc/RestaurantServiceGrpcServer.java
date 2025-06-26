package asw.efood.restaurantservice.grpc;

import asw.efood.restaurantservice.domain.*;

import asw.efood.restaurantservice.api.grpc.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.*; 
import java.util.stream.*; 
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.IOException;

import io.grpc.inprocess.InProcessServerBuilder; 
import io.grpc.inprocess.InProcessChannelBuilder; 
import java.util.concurrent.TimeUnit; 
import java.util.concurrent.ExecutionException; 

@Component
public class RestaurantServiceGrpcServer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired 
	private RestaurantService restaurantService;

	// Server effettivo 
	@Value("${asw.efood.restaurantservice.grpc.port}")
    private int port;

	// Server inprocess, usato per il test 
	@Value("${asw.efood.restaurantservice.grpc.inprocessserver:false}")
    private boolean inProcessServer;
	@Value("${asw.efood.restaurantservice.grpc.inprocessservername:}")
    private String serverName;

    private Server server;
	
	@PostConstruct
	public void initServer() throws IOException {
		if (inProcessServer) {
			startInprocessServer(); 
		} else {
			startServer(); 
		}
	}

	private void startServer() throws IOException {
		server = ServerBuilder.forPort(port)
				.addService(new RestaurantServiceImpl())
				.build()
				.start();
		logger.info("Server started, listening on " + port);
	}

	private void startInprocessServer() throws IOException {
		server = InProcessServerBuilder
				.forName(serverName)
				.addService(new RestaurantServiceImpl())
				.directExecutor()
				.build()
				.start(); 
		logger.info("Inprocess server " + serverName + " started");
	}

	@PreDestroy
	public void stopServer() throws InterruptedException {
		if (server!=null) {
			// logger.info("*** shutting down gRPC server since JVM is shutting down");
			server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			logger.info("*** server shut down");
		}
	}
	
    private class RestaurantServiceImpl extends RestaurantServiceGrpc.RestaurantServiceImplBase {

        @Override
        public void createRestaurant(CreateRestaurantRequest request, StreamObserver<CreateRestaurantReply> responseObserver) {
            String name = request.getName();
            String location = request.getLocation();
			logger.info("gRPC CALL: createRestaurant " + name + ", " + location); 
			try {
				Restaurant restaurant = restaurantService.createRestaurant(name, location);
				logger.info("gRPC CALL: createRestaurant " + name + ", " + location + " --> CREATED"); 
				CreateRestaurantReply reply = CreateRestaurantReply.newBuilder()
						.setRestaurantId(restaurant.getId())
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				logger.info("gRPC CALL: createRestaurant " + name + ", " + location + " --> NOT CREATED"); 
				responseObserver.onError(io.grpc.Status.ALREADY_EXISTS.withDescription("Restaurant not created").asRuntimeException());
			}				
        }

        @Override
        public void getRestaurantById(GetRestaurantByIdRequest request, StreamObserver<GetRestaurantReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			logger.info("gRPC CALL: getRestaurant " + restaurantId); 
			try {
				Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
				GetRestaurantReply reply = GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				logger.info("gRPC CALL: getRestaurant " + restaurantId + " --> NOT FOUND"); 
				responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Restaurant not found").asRuntimeException());
			}
        }

        @Override
        public void getAllRestaurants(GetAllRestaurantsRequest request, StreamObserver<GetRestaurantsReply> responseObserver) {
			logger.info("gRPC CALL: getAllRestaurants"); 
			Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
			List<GetRestaurantReply> rr = 
				restaurants.stream() 
					.map(restaurant -> GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build())
					.collect(Collectors.toList()); 
            GetRestaurantsReply reply = GetRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
		
        @Override
        public void getRestaurantByNameAndLocation(GetRestaurantByNameAndLocationRequest request, StreamObserver<GetRestaurantReply> responseObserver) {
            String name = request.getName(); 
            String location = request.getLocation(); 
			logger.info("gRPC CALL: getRestaurant " + name + " " + location); 
			try {
				Restaurant restaurant = restaurantService.getRestaurant(name, location);
				GetRestaurantReply reply = GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				logger.info("gRPC CALL: getRestaurant " + name + " " + location + " --> NOT FOUND"); 
				responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Restaurant not found").asRuntimeException());
			}
        }

        @Override
        public void getRestaurantsByLocation(GetRestaurantsByLocationRequest request, StreamObserver<GetRestaurantsReply> responseObserver) {
            String location = request.getLocation(); 
			logger.info("gRPC CALL: getRestaurants " + location); 
			Collection<Restaurant> restaurants = restaurantService.getRestaurantsByLocation(location);
			List<GetRestaurantReply> rr = 
				restaurants.stream() 
					.map(restaurant -> GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build())
					.collect(Collectors.toList()); 
            GetRestaurantsReply reply = GetRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void createRestaurantMenu(CreateRestaurantMenuRequest request, StreamObserver<CreateRestaurantMenuReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			List<MenuItem> menuItems = 
				request.getMenuItemsList()
					.stream() 
					.map(item -> new MenuItem(item.getId(), item.getName(), item.getPrice()))
					.collect(Collectors.toList()); 
			logger.info("gRPC CALL: createRestaurantMenu " + restaurantId + ", " + menuItems); 
			try {
				RestaurantMenu menu = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);
				CreateRestaurantMenuReply reply = CreateRestaurantMenuReply.newBuilder()
						.setRestaurantId(menu.getId())
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				logger.info("gRPC CALL: createRestaurantMenu " + restaurantId + ", " + menuItems + " --> NOT CREATED"); 
				responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Restaurant menu not found").asRuntimeException());
			}
        }

        @Override
        public void getRestaurantMenu(GetRestaurantMenuRequest request, StreamObserver<GetRestaurantMenuReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			logger.info("gRPC CALL: getRestaurantMenu " + restaurantId); 
			try {
				RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
				List<RestaurantMenuItem> menuItems = 
					menu.getMenuItems()
						.stream() 
						.map(item -> RestaurantMenuItem.newBuilder()
							.setId(item.getId())
							.setName(item.getName())
							.setPrice(item.getPrice())
							.build())
						.collect(Collectors.toList()); 
				GetRestaurantMenuReply reply = GetRestaurantMenuReply.newBuilder()
							.setRestaurantId(restaurantId)
							.addAllMenuItems(menuItems)
							.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
 			} catch (RestaurantServiceException e) {
				logger.info("gRPC CALL: getRestaurantMenu " + restaurantId + " --> NOT FOUND"); 
				responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Restaurant menu not found").asRuntimeException());
			}
       }
		
	}

}
