package asw.efood.samplerestaurantclient.restaurantclient.rest;

import asw.efood.samplerestaurantclient.domain.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

import java.util.logging.Logger;

import java.util.*; 

@Service
public class RestaurantClientAsyncRestAdapter implements RestaurantClientAsyncPort {

    private Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantClientPort restaurantClientAdapter;

    @Value("${asw.efood.restaurantclient.async.delay:0}")
    private int asyncDelay;

	@Async
	public CompletableFuture<Long> createRestaurantAsync(String name, String location) throws RestaurantServiceException {
        logger.info("Creating restaurant " + name + " " + location + " (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.createRestaurant(name, location)); 
	} 
	
	@Async
	public CompletableFuture<Restaurant> getRestaurantAsync(Long restaurantId) throws RestaurantServiceException {
        logger.info("Looking for restaurant with " + restaurantId + " (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.getRestaurant(restaurantId)); 
	} 
	
	@Async
	public CompletableFuture<List<Restaurant>> getAllRestaurantsAsync() {
        logger.info("Looking for all restaurants (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.getAllRestaurants()); 
	} 

	@Async
	public CompletableFuture<Restaurant> getRestaurantByNameAndLocationAsync(String name, String location) throws RestaurantServiceException {
        logger.info("Looking for restaurant with " + name + " and " + location + " (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.getRestaurantByNameAndLocation(name, location)); 
	} 

	@Async
	public CompletableFuture<List<Restaurant>> getRestaurantsByLocationAsync(String location) {
        logger.info("Looking for restaurants in " + location + " (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.getRestaurantsByLocation(location)); 
	} 

	/* Introduce un ritardo di delay millisecondi. */
	private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}

}
