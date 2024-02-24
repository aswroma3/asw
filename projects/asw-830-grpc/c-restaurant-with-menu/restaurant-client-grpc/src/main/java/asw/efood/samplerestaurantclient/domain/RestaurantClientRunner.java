package asw.efood.samplerestaurantclient.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.*;

@Component
public class RestaurantClientRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantClientPort restaurantClientAdapter;

	public void run(String[] args) throws InterruptedException {
		logger.info("*** Cerco il ristorante con id=1L ***");
		logger.info(restaurantClientAdapter.getRestaurant(1L).toString());
		logger.info("*** Cerco il menu del ristorante con id=2L ***");
		logger.info(restaurantClientAdapter.getRestaurantMenu(2L).toString());

		logger.info("*** Creo un nuovo ristorante Alfa ***");
		Long newRestaurantId = restaurantClientAdapter.createRestaurant("Alfa", "Roma");
		logger.info(newRestaurantId.toString());
		logger.info("*** Cerco il ristorante appena creato ***");
		logger.info(restaurantClientAdapter.getRestaurant(newRestaurantId).toString());

		logger.info("*** Creo il menu del ristorante appena creato ***");
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add( new MenuItem("SPV", "Spaghetti alle vongole", 11.0) );
		menuItems.add( new MenuItem("LIS", "Linguine allo scoglio", 12.0) );
		logger.info(restaurantClientAdapter.createRestaurantMenu(newRestaurantId, menuItems).toString());
		logger.info("*** Cerco il menu del ristorante appena creato ***");
		logger.info(restaurantClientAdapter.getRestaurantMenu(newRestaurantId).toString());

		logger.info("*** Cerco tutti i ristoranti ***");
		logger.info(restaurantClientAdapter.getAllRestaurants().toString());
	}
}
