package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException; 

import java.util.*; 

import java.util.logging.Logger;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantEventPublisher restaurantEventPublisher;

 	public Restaurant createRestaurant(String name, String location) throws RestaurantServiceException {
		logger.info("Devo creare il ristorante " + name + " " + location);
		Restaurant restaurant = new Restaurant(name, location); 
		try {
			restaurant = restaurantRepository.save(restaurant);
			logger.info("Adesso pubblico l'evento per il ristorante " + name + " " + location);
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
		if (restaurant==null) { 
			throw new RestaurantServiceException("Unable to find restaurant with " + id); 
		}
		return restaurant; 
	}

 	public Restaurant getRestaurant(String name, String location) throws RestaurantServiceException {
		Restaurant restaurant = restaurantRepository.findByNameAndLocation(name, location).orElse(null);
		if (restaurant==null) { 
			throw new RestaurantServiceException("Unable to find restaurant with " + name + " and " + location); 
		}
		return restaurant; 
	}

	public Collection<Restaurant> getAllRestaurants() {
		Collection<Restaurant> restaurants = restaurantRepository.findAll(); 
		return restaurants;
	}
	
	public Collection<Restaurant> getRestaurantsByLocation(String location) {
		Collection<Restaurant> restaurants = restaurantRepository.findAllByLocation(location);
		return restaurants;
	}	

}

