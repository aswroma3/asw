package asw.efood.restaurantservice.grpc;

import asw.efood.restaurantservice.domain.*;

import asw.efood.restaurantservice.api.grpc.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;
import java.util.*; 
import java.util.stream.*; 

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Component
public class RestaurantServiceGrpcServer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired 
	private RestaurantService restaurantService;

	@Value("${asw.efood.restaurantservice.grpc.port}")
    private int port;

    private Server server;
	
    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new RestaurantServiceImpl())
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

    private class RestaurantServiceImpl extends RestaurantServiceGrpc.RestaurantServiceImplBase {

        @Override
        public void createRestaurant(CreateRestaurantRequest request, StreamObserver<CreateRestaurantReply> responseObserver) {
            String name = request.getName();
            String location = request.getLocation();
			logger.info("gRPC CALL: createRestaurant " + name + ", " + location); 
            Restaurant restaurant = restaurantService.createRestaurant(name, location);
            CreateRestaurantReply reply = CreateRestaurantReply.newBuilder()
                    .setRestaurantId(restaurant.getId())
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
            Restaurant restaurant = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);
            CreateRestaurantMenuReply reply = CreateRestaurantMenuReply.newBuilder()
                    .setRestaurantId(restaurant.getId())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void getRestaurant(GetRestaurantRequest request, StreamObserver<GetRestaurantReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			logger.info("gRPC CALL: getRestaurant " + restaurantId); 
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
            GetRestaurantReply reply = GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void getAllRestaurants(GetAllRestaurantsRequest request, StreamObserver<GetAllRestaurantsReply> responseObserver) {
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
            GetAllRestaurantsReply reply = GetAllRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void getRestaurantMenu(GetRestaurantMenuRequest request, StreamObserver<GetRestaurantMenuReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			logger.info("gRPC CALL: getRestaurantMenu " + restaurantId); 
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
        }
    }

}
