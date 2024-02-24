package asw.efood.samplerestaurantclient.restaurantservice.rest;

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
	public CompletableFuture<Long> createRestaurantAsync(String name, String location) {
        logger.info("Creating restaurant " + name + " (async)");
		/* introduce un ritardo fittizio */ 
		sleep(asyncDelay); 
		return CompletableFuture.completedFuture(restaurantClientAdapter.createRestaurant(name, location)); 
	} 
	
	@Async
	public CompletableFuture<Restaurant> getRestaurantAsync(Long restaurantId) {
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

	/* Introduce un ritardo di delay millisecondi. */
	private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}

}
