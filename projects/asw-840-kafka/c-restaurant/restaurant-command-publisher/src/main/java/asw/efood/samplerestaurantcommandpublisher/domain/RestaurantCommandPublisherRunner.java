package asw.efood.samplerestaurantcommandpublisher.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.*;

@Component
public class RestaurantCommandPublisherRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantCommandPublisher restaurantCommandPublisher;

	public void run(String[] args) throws InterruptedException {
		Command command; 
		
		command = new CreateRestaurantCommand("Da Mario", "Roma"); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);
		
		command = new CreateRestaurantCommand("Da Giovanna", "Torino"); 
		logger.info("COMMAND PUBLISHER: " + command); 
		restaurantCommandPublisher.publish(command);
	}
}
