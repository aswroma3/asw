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

	private static String[] NAMES = new String[] { "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" }; 
	private static String[] LOCATIONS = new String[] { "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" }; 
	private static MenuItem[] MENU_ITEMS = new MenuItem[] { 
			new MenuItem("SPV", "Spaghetti alle vongole", 12.0), 
			new MenuItem("LIS", "Linguine allo scoglio", 15.0), 
			new MenuItem("CAR", "Carbonara", 12.0), 
			new MenuItem("GRI", "Gricia", 12.0), 
			new MenuItem("AMA", "Amatriciana", 12.0)
		}; 
//	private static String[] NAMES = new String[] { "Da Pino" }; 
//	private static String[] LOCATIONS = new String[] { "Roma" }; 
	private Random random = new Random(); 

	public void run(String... args) throws InterruptedException {
//		logger.info("*** Cerco il ristorante con id=1L ***");
//		logger.info(restaurantClientAdapter.getRestaurant(1L).toString());
		try {
			logger.info("*** Cerco il ristorante con id=1L ***");
			logger.info(restaurantClientAdapter.getRestaurant(1L).toString());
		} catch (RestaurantServiceException e) {
			logger.info("*** RestaurantServiceException: " + e.getMessage() + " ***");
		}

		logger.info("*** Cerco il menu del ristorante con id=1L ***");
		logger.info(restaurantClientAdapter.getRestaurantMenu(1L).toString());

//		logger.info("*** Creo un nuovo ristorante Alfa ***");
//		Long newRestaurantId = restaurantClientAdapter.createRestaurant("Alfa", "Roma");
//		logger.info(newRestaurantId.toString());
//		logger.info("*** Cerco il ristorante appena creato ***");
//		logger.info(restaurantClientAdapter.getRestaurant(newRestaurantId).toString());
		try { 
			String name = randomName(); 
			String location = randomLocation(); 
			logger.info("*** Creo un nuovo ristorante " + name + " a " + location + " ***" );
			Long newRestaurantId = restaurantClientAdapter.createRestaurant(name, location);
			logger.info(newRestaurantId.toString());
			logger.info("*** Cerco il ristorante appena creato ***");
			logger.info(restaurantClientAdapter.getRestaurantByNameAndLocation(name, location).toString());

			logger.info("*** Creo il menu del ristorante appena creato ***");
			List<MenuItem> menuItems = randomMenuItems(); 
			logger.info(restaurantClientAdapter.createRestaurantMenu(newRestaurantId, menuItems).toString());
			logger.info("*** Cerco il menu del ristorante appena creato ***");
			logger.info(restaurantClientAdapter.getRestaurantMenu(newRestaurantId).toString());
		} catch (RestaurantServiceException e) {
			logger.info("*** RestaurantServiceException: " + e.getMessage() + " ***");
		}

		logger.info("*** Cerco tutti i ristoranti ***");
		logger.info(restaurantClientAdapter.getAllRestaurants().toString());

		logger.info("*** Cerco tutti i ristoranti di Roma ***");
		logger.info(restaurantClientAdapter.getRestaurantsByLocation("Roma").toString());
	}

	private String randomName() {
		int randomIndex = random.nextInt(NAMES.length);
		return NAMES[randomIndex];
	}

	private String randomLocation() {
		int randomIndex = random.nextInt(LOCATIONS.length);
		return LOCATIONS[randomIndex];
	}
	
	private List<MenuItem> randomMenuItems() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (int i=0; i<3; i++) {
			int randomIndex = random.nextInt(MENU_ITEMS.length);
			menuItems.add( MENU_ITEMS[randomIndex] );
		}
		return menuItems; 
	}

}
