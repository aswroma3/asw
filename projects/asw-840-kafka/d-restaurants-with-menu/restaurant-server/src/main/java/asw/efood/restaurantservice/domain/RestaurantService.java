package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException; 

import java.util.*; 
import java.util.stream.*;

import java.util.logging.Logger;

@Service
public class RestaurantService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantMenuRepository restaurantMenuRepository;

	@Autowired
	private RestaurantEventPublisher restaurantEventPublisher;

 	public Restaurant createRestaurant(String name, String location) throws RestaurantServiceException {
		Restaurant restaurant = new Restaurant(name, location); 
		try {
			restaurant = restaurantRepository.save(restaurant);
			RestaurantMenu menu = new RestaurantMenu(restaurant.getId()); 
			menu = restaurantMenuRepository.save(menu); 
			DomainEvent event = new RestaurantCreatedEvent(restaurant.getId(), restaurant.getName(), restaurant.getLocation());
			restaurantEventPublisher.publish(event);
			return restaurant; 
		} catch (DataAccessException e) {
			logger.info("DataAccessException:" + e.toString());
			/* ristorante non salvato per violazione del vincolo unique */ 
			String errorMessage = "Unable to create restaurant with " + name + " and " + location; 
			throw new RestaurantServiceException(errorMessage); 
		}
	}

 	public Restaurant getRestaurant(Long id) throws RestaurantServiceException {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		if (restaurant!=null) { 
			return restaurant; 
		} else {
			throw new RestaurantServiceException("Unable to find restaurant with " + id); 
		}
	}

 	public Restaurant getRestaurant(String name, String location) throws RestaurantServiceException {
		Restaurant restaurant = restaurantRepository.findByNameAndLocation(name, location).orElse(null);
		if (restaurant!=null) { 
			return restaurant; 
		} else {
			throw new RestaurantServiceException("Unable to find restaurant with " + name + " and " + location); 
		}
	}

	public Collection<Restaurant> getAllRestaurants() {
		Collection<Restaurant> restaurants = restaurantRepository.findAll(); 
		return restaurants;
	}
	
	public Collection<Restaurant> getRestaurantsByLocation(String location) {
		Collection<Restaurant> restaurants = restaurantRepository.findAllByLocation(location);
		return restaurants;
	}	

 	public RestaurantMenu createOrUpdateRestaurantMenu(Long id, List<MenuItem> menuItems) throws RestaurantServiceException {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		if (restaurant!=null) { 
			RestaurantMenu menu = new RestaurantMenu(restaurant.getId(), menuItems);
			menu = restaurantMenuRepository.save(menu);
			DomainEvent event = createRestaurantMenuCreatedOrUpdatedEvent(menu);
			restaurantEventPublisher.publish(event);
			return menu;
		} else {
			throw new RestaurantServiceException("Unable to create or update restaurant menu: unable to find restaurant with " + id); 
		}
	}

 	public RestaurantMenu createOrUpdateRestaurantMenu(String name, String location, List<MenuItem> menuItems) throws RestaurantServiceException {
		Restaurant restaurant = restaurantRepository.findByNameAndLocation(name, location).orElse(null);
		if (restaurant!=null) { 
			RestaurantMenu menu = new RestaurantMenu(restaurant.getId(), menuItems);
			menu = restaurantMenuRepository.save(menu);
			DomainEvent event = createRestaurantMenuCreatedOrUpdatedEvent(menu);
			restaurantEventPublisher.publish(event);
			return menu;
		} else {
			throw new RestaurantServiceException("Unable to create or update restaurant menu: unable to find restaurant with " + name + " and " + location); 
		}
	}

	private DomainEvent createRestaurantMenuCreatedOrUpdatedEvent(RestaurantMenu menu) {
		List<MenuItemElement> menuItemElements = 
				menu.getMenuItems()
					.stream()
					.map(item -> new MenuItemElement(item.getId(), item.getName(), item.getPrice()))
					.collect(Collectors.toList());
		DomainEvent event = new RestaurantMenuCreatedOrUpdatedEvent(menu.getId(), menuItemElements);
		return event;
	}
	
 	public RestaurantMenu getRestaurantMenu(Long id) throws RestaurantServiceException {
		RestaurantMenu menu = restaurantMenuRepository.findById(id).orElse(null);
		if (menu!=null) { 
			return menu; 
		} else {
			throw new RestaurantServiceException("Unable to find restaurant menu with " + id); 
		}
	}
	
}

