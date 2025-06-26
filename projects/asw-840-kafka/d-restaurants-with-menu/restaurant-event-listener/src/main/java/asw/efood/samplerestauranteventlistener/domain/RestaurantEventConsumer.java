package asw.efood.samplerestauranteventlistener.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.*;

@Service
public class RestaurantEventConsumer {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	public void onEvent(DomainEvent event) {
		if (event instanceof RestaurantCreatedEvent evt) {
			onRestaurantCreated(evt); 
		} else if (event instanceof RestaurantMenuCreatedOrUpdatedEvent evt) {
			onRestaurantMenuCreatedOrUpdated(evt); 
		} else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	
	public void onRestaurantCreated(RestaurantCreatedEvent event) {
		Restaurant restaurant = new Restaurant(event.getId(), event.getName(), event.getLocation());
		logger.info("CREATED RESTAURANT: " + restaurant);
	}

	public void onRestaurantMenuCreatedOrUpdated(RestaurantMenuCreatedOrUpdatedEvent event) {
		RestaurantMenu menu = new RestaurantMenu(event.getId(), menuItemElementsToMenuItems(event.getMenuItems()));
		logger.info("CREATED OR UPDATED RESTAURANT MENU: " + menu);
	}

	private List<MenuItem> menuItemElementsToMenuItems(List<MenuItemElement> items) {
		return items 
				.stream()
				.map(i -> menuItemElementToMenuItem(i))
				.collect(Collectors.toList());
	}

	private MenuItem menuItemElementToMenuItem(MenuItemElement item) {
		return new MenuItem(item.getId(), item.getName(), item.getPrice());
	}

}
