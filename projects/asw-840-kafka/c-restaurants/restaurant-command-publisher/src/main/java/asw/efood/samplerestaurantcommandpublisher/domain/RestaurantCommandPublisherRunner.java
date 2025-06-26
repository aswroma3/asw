package asw.efood.samplerestaurantcommandpublisher.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.*;
import java.util.Random;

@Component
public class RestaurantCommandPublisherRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantCommandPublisher restaurantCommandPublisher;

	private static String[] NAMES = new String[] { "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" }; 
	private static String[] LOCATIONS = new String[] { "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" }; 
	private Random random = new Random(); 

	public void run(String... args) throws InterruptedException {
		Command command; 
		String name, location; 
		
//		command = new CreateRestaurantCommand("Da Mario", "Roma"); 
//		logger.info("COMMAND PUBLISHER: " + command); 
//		restaurantCommandPublisher.publish(command);
		name = randomName(); 
		location = randomLocation(); 
		command = new CreateRestaurantCommand(name, location); 
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

		// ripete la creazione dell'ultimo ristorante, il che solleva un'eccezione 
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

}
