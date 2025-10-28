package asw.efood.samplerestaurantclient.restaurantclient.grpc;

import asw.efood.samplerestaurantclient.domain.*;

import asw.efood.restaurantservice.api.grpc.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value; 

import java.util.concurrent.ExecutionException;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.logging.Logger;
import java.util.*; 
import java.util.stream.*; 

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.IOException; 

import io.grpc.inprocess.InProcessChannelBuilder; 

@Service
public class RestaurantClientGrpcAdapter implements RestaurantClientPort {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

    private ManagedChannel channel;
    private RestaurantServiceGrpc.RestaurantServiceFutureStub futureStub;

	// Client effettivo 
	@Value("${asw.efood.restaurantservice.grpc.host}")
    private String host;
	@Value("${asw.efood.restaurantservice.grpc.port}")
    private int port;

	// Client usato per il test  
	@Value("${asw.efood.restaurantservice.grpc.inprocessserver:false}")
    private boolean inProcessServer;
	@Value("${asw.efood.restaurantservice.grpc.inprocessservername:}")
    private String serverName;

	@PostConstruct
    public void init() throws IOException {
		if (inProcessServer) {
			initInprocessChannel(); 
		} else {
			initChannel(); 
		}
        this.futureStub = RestaurantServiceGrpc.newFutureStub(channel);
	}

	private void initChannel() {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). 
				// For the example we disable TLS to avoid needing certificates.
                .usePlaintext()
                .build();
	}

	private void initInprocessChannel() throws IOException {
        this.channel = InProcessChannelBuilder
            .forName(serverName)
            .directExecutor()
            .build();
	}

    @PreDestroy
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

	/* Tutte le operazioni remote vengono invocate usando il future stub. */ 

	public Long createRestaurant(String name, String location) throws RestaurantServiceException {
		logger.info("Creating restaurant " + name + " " + location);
		CreateRestaurantRequest request = CreateRestaurantRequest.newBuilder()
				.setName(name)
				.setLocation(location)
				.build();
		try {
			ListenableFuture<CreateRestaurantReply> futureReply = futureStub.createRestaurant(request);
			CreateRestaurantReply reply = futureReply.get();
			Long restaurantId = reply.getRestaurantId();
			logger.info("Restaurant created with: " + restaurantId);
			return restaurantId;
		} catch (ExecutionException e) {
			logger.info("Restaurant not created, with exception " + e.getMessage());
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: " + e.getStatus());
		} catch (InterruptedException e) {
			logger.info("InterruptedException: " + e.toString());
		}
		throw new RestaurantServiceException("RestaurantServiceException for createRestaurant(" + name + ", " + location + ")");
	}

	public Restaurant getRestaurant(Long restaurantId) throws RestaurantServiceException {
		logger.info("Looking for restaurant with " + restaurantId);
		GetRestaurantByIdRequest request = GetRestaurantByIdRequest.newBuilder().setRestaurantId(restaurantId).build();
		try {
			ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantById(request);
			GetRestaurantReply reply = futureReply.get();
			Restaurant restaurant = new Restaurant(reply.getRestaurantId(), reply.getName(), reply.getLocation()); 
			logger.info("Restaurant found: " + restaurant.toString());
			return restaurant;
		} catch (ExecutionException e) {
			logger.info("Restaurant not found, with exception " + e.getMessage());
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: " + e.getStatus());
		} catch (InterruptedException e) {
			logger.info("InterruptedException: " + e.toString());
		}
		throw new RestaurantServiceException("RestaurantServiceException for getRestaurant(" + restaurantId + ")");
	}

	public Restaurant getRestaurantByNameAndLocation(String name, String location) throws RestaurantServiceException {
		logger.info("Looking for restaurant with " + name + " and " + location);
		GetRestaurantByNameAndLocationRequest request = 
			GetRestaurantByNameAndLocationRequest.newBuilder().setName(name).setLocation(location).build();
		try {
			ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantByNameAndLocation(request);
			GetRestaurantReply reply = futureReply.get();
			Restaurant restaurant = new Restaurant(reply.getRestaurantId(), reply.getName(), reply.getLocation()); 
			logger.info("Restaurant found: " + restaurant.toString());
			return restaurant;
		} catch (ExecutionException e) {
			logger.info("Restaurant not found, with exception " + e.getMessage());
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: " + e.getStatus());
		} catch (InterruptedException e) {
			logger.info("InterruptedException: " + e.toString());
		}
		throw new RestaurantServiceException("RestaurantServiceException for getRestaurant(" + name + ", " + location + ")");
	}

	public List<Restaurant> getAllRestaurants() {
		logger.info("Looking for all restaurants");
		List<Restaurant> restaurants = null; 
		GetAllRestaurantsRequest request = GetAllRestaurantsRequest.newBuilder().build();
		try {
			ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getAllRestaurants(request);
			GetRestaurantsReply reply = futureReply.get();
			if (reply != null) {
				restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> new Restaurant(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getLocation()) )
					.collect(Collectors.toList()); 
				logger.info("Restaurants found: " + restaurants.toString());
			} else {
				logger.info("Restaurants not found");
			}
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: " + e.getStatus());
		} catch (InterruptedException e) {
			logger.info("InterruptedException: " + e.toString());
		} catch (ExecutionException e) {
			logger.info("ExecutionException: " + e.toString());
		}
		return restaurants;
	}

	public List<Restaurant> getRestaurantsByLocation(String location) {
		logger.info("Looking for restaurants in " + location);
		List<Restaurant> restaurants = null; 
		GetRestaurantsByLocationRequest request = GetRestaurantsByLocationRequest.newBuilder().setLocation(location).build();
		try {
			ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getRestaurantsByLocation(request);
			GetRestaurantsReply reply = futureReply.get();
			if (reply != null) {
				restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> new Restaurant(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getLocation()) )
					.collect(Collectors.toList()); 
				logger.info("Restaurants in " + location + " found: " + restaurants.toString());
			} else {
				logger.info("Restaurants in " + location + " not found");
			}
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: " + e.getStatus());
		} catch (InterruptedException e) {
			logger.info("InterruptedException: " + e.toString());
		} catch (ExecutionException e) {
			logger.info("ExecutionException: " + e.toString());
		}
		return restaurants;
	}

	public Long createRestaurantMenu(Long restaurantId, List<MenuItem> menuItems) throws RestaurantServiceException {
        logger.info("Creating menu for restaurant with " + restaurantId);
		List<RestaurantMenuItem> restaurantMenuItems = 
			menuItems.stream() 
				.map( item -> RestaurantMenuItem.newBuilder()
								.setId(item.getId())
								.setName(item.getName())
								.setPrice(item.getPrice())
								.build() ) 
				.collect(Collectors.toList()); 
        CreateRestaurantMenuRequest request = CreateRestaurantMenuRequest.newBuilder()
                .setRestaurantId(restaurantId)
                .addAllMenuItems(restaurantMenuItems)
                .build();
        try {
            ListenableFuture<CreateRestaurantMenuReply> futureReply = futureStub.createRestaurantMenu(request);
            CreateRestaurantMenuReply reply = futureReply.get();
			logger.info("Restaurant menu created for: " + restaurantId);
			return restaurantId;
        } catch (StatusRuntimeException e) {
            logger.info("RPC failed: " + e.getStatus());
        } catch (InterruptedException e) {
            logger.info("InterruptedException: " + e.toString());
        } catch (ExecutionException e) {
            logger.info("ExecutionException: " + e.toString());
        }
		throw new RestaurantServiceException("RestaurantServiceException for createRestaurantMenu(" + restaurantId + ", " + menuItems + ")");
    }

	public List<MenuItem> getRestaurantMenu(Long restaurantId) throws RestaurantServiceException {
        logger.info("Looking for menu of restaurant with " + restaurantId);
        GetRestaurantMenuRequest request = GetRestaurantMenuRequest.newBuilder().setRestaurantId(restaurantId).build();
        try {
            ListenableFuture<GetRestaurantMenuReply> futureReply = futureStub.getRestaurantMenu(request);
            GetRestaurantMenuReply reply = futureReply.get();
			List<MenuItem> menuItems = reply.getMenuItemsList().stream()
				.map( item -> new MenuItem(item.getId(), item.getName(), item.getPrice()) )
				.collect(Collectors.toList()); 
			logger.info("Restaurant menu found: " + menuItems.toString());
			return menuItems;
        } catch (StatusRuntimeException e) {
            logger.info("RPC failed: " + e.getStatus());
        } catch (InterruptedException e) {
            logger.info("InterruptedException: " + e.toString());
        } catch (ExecutionException e) {
            logger.info("ExecutionException: " + e.toString());
        }
		throw new RestaurantServiceException("RestaurantServiceException for getRestaurantMenu(" + restaurantId + ")");
    }

}
