package asw.efood.samplerestaurantcommandpublisher.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.*; 

import java.util.logging.*;

@Component
public class RestaurantCommandPublisherRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantCommandPublisher restaurantCommandPublisher;

	private static String[] NAMES = new String[] { "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" }; 
	private static String[] LOCATIONS = new String[] { "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" }; 
	private static MenuItemElement[] MENU_ITEMS = new MenuItemElement[] { 
			new MenuItemElement("SPV", "Spaghetti alle vongole", 12.0), 
			new MenuItemElement("LIS", "Linguine allo scoglio", 15.0), 
			new MenuItemElement("CAR", "Carbonara", 12.0), 
			new MenuItemElement("GRI", "Gricia", 12.0), 
			new MenuItemElement("AMA", "Amatriciana", 12.0)
		}; 
	private Random random = new Random(); 

	public void run(String... args) throws InterruptedException {
		Command command; 
		String name, location; 
		List<MenuItemElement> menuItems;
		
//		command = new CreateRestaurantCommand("Da Mario", "Roma"); 
//		logger.info("COMMAND PUBLISHER: " + command); 
//		restaurantCommandPublisher.publish(command);
		name = randomName(); 
		location = randomLocation(); 
		command = new CreateRestaurantCommand(name, location); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);
		
		menuItems = randomMenuItems(); 
		command = new CreateOrUpdateRestaurantMenuCommand(name, location, menuItems); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);

//		command = new CreateRestaurantCommand("Da Giovanna", "Torino"); 
//		logger.info("COMMAND PUBLISHER: " + command); 
//		restaurantCommandPublisher.publish(command);
		name = randomName(); 
		location = randomLocation(); 
		command = new CreateRestaurantCommand(name, location); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);

		menuItems = randomMenuItems(); 
		command = new CreateOrUpdateRestaurantMenuCommand(name, location, menuItems); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);

//		List<MenuItemElement> menuItems = randomMenuItems(); 
//		command = new CreateOrUpdateRestaurantMenuCommand(name, location, menuItems); 
//		logger.info("COMMAND PUBLISHER: " + command); 
//		restaurantCommandPublisher.publish(command);

//		// ripete la creazione dell'ultimo ristorante, il che solleva un'eccezione 
//		command = new CreateRestaurantCommand(name, location); 
//		logger.info("COMMAND PUBLISHER: " + command); 
//		restaurantCommandPublisher.publish(command);
	}

	private String randomName() {
		int randomIndex = random.nextInt(NAMES.length);
		return NAMES[randomIndex];
	}

	private String randomLocation() {
		int randomIndex = random.nextInt(LOCATIONS.length);
		return LOCATIONS[randomIndex];
	}

	private List<MenuItemElement> randomMenuItems() {
		List<MenuItemElement> menuItems = new ArrayList<>();
		for (int i=0; i<3; i++) {
			int randomIndex = random.nextInt(MENU_ITEMS.length);
			menuItems.add( MENU_ITEMS[randomIndex] );
		}
		return menuItems; 
	}
	
}
