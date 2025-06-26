package asw.efood.samplerestaurantclient.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException; 

import java.util.*;
import java.util.logging.*;

@Component
public class RestaurantAsyncClientRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantClientAsyncPort restaurantClientAsyncAdapter;

	private static String[] NAMES = new String[] { "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" }; 
	private static String[] LOCATIONS = new String[] { "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" }; 
	private Random random = new Random(); 

	public void run(String... args) throws InterruptedException, ExecutionException {
//		logger.info("*** Cerco il ristorante con id=1L ***");
//		CompletableFuture<Restaurant> futureRestaurant = restaurantClientAsyncAdapter.getRestaurantAsync(1L);
//		// logger.info("*** Cerco il ristorante con id=1L *** -> done");
//		logger.info(futureRestaurant.get().toString());
		CompletableFuture<Restaurant> futureRestaurant; 
		try {
			logger.info("*** Cerco il ristorante con id=1L ***");
			futureRestaurant = restaurantClientAsyncAdapter.getRestaurantAsync(1L);
			// logger.info("*** Cerco il ristorante con id=1L *** -> done");
			logger.info(futureRestaurant.get().toString());
		} catch (RestaurantServiceException e) {
			logger.info("*** RestaurantServiceException: " + e.getMessage() + " ***");
		}

//		logger.info("*** Creo un nuovo ristorante Alfa ***");
//		CompletableFuture<Long> futureRestaurantId = restaurantClientAsyncAdapter.createRestaurantAsync("Alfa", "Roma");
//		// logger.info("*** Creo un nuovo ristorante Alfa *** -> done");
//		Long newRestaurantId = futureRestaurantId.get(); 
//		logger.info(newRestaurantId.toString());
//		logger.info("*** Cerco il ristorante appena creato ***");
//		futureRestaurant = restaurantClientAsyncAdapter.getRestaurantAsync(newRestaurantId);
//		// logger.info("*** Cerco il ristorante appena creato *** -> done");
//		logger.info(futureRestaurant.get().toString());
		try { 
			String name = randomName(); 
			String location = randomLocation(); 
			logger.info("*** Creo un nuovo ristorante " + name + " a " + location + " ***" );
			CompletableFuture<Long> futureRestaurantId = restaurantClientAsyncAdapter.createRestaurantAsync(name, location);
			// logger.info("*** Creo un nuovo ristorante Alfa *** -> done");
			Long newRestaurantId = futureRestaurantId.get(); 
			logger.info(newRestaurantId.toString());
			logger.info("*** Cerco il ristorante appena creato ***");
			futureRestaurant = restaurantClientAsyncAdapter.getRestaurantByNameAndLocationAsync(name, location);
			// logger.info("*** Cerco il ristorante appena creato *** -> done");
			logger.info(futureRestaurant.get().toString());
		} catch (RestaurantServiceException e) {
			logger.info("*** RestaurantServiceException: " + e.getMessage() + " ***");
		}			

		logger.info("*** Cerco tutti i ristoranti ***");
		CompletableFuture<List<Restaurant>> futureRestaurants = restaurantClientAsyncAdapter.getAllRestaurantsAsync(); 
		// logger.info("*** Cerco tutti i ristoranti *** -> done");
		logger.info(futureRestaurants.get().toString());

		logger.info("*** Cerco tutti i ristoranti di Roma ***");
		futureRestaurants = restaurantClientAsyncAdapter.getRestaurantsByLocationAsync("Roma"); 
		// logger.info("*** Cerco tutti i ristoranti di Roma *** -> done");
		logger.info(futureRestaurants.get().toString());
	}

	private String randomName() {
		int randomIndex = random.nextInt(NAMES.length);
		return NAMES[randomIndex];
	}

	private String randomLocation() {
		int randomIndex = random.nextInt(LOCATIONS.length);
		return LOCATIONS[randomIndex];
	}
	
}
