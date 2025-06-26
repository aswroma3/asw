package asw.efood.samplerestauranteventlistener.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class RestaurantEventConsumer {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	public void onEvent(DomainEvent event) {
		if (event instanceof RestaurantCreatedEvent evt) {
			onRestaurantCreated(evt); 
		} else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	
	public void onRestaurantCreated(RestaurantCreatedEvent event) {
		Restaurant restaurant = new Restaurant(event.getId(), event.getName(), event.getLocation());
		logger.info("CREATED RESTAURANT: " + restaurant);
	}

}
