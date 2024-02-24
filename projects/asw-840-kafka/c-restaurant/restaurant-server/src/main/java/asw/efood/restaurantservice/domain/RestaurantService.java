package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantEventPublisher restaurantEventPublisher;

 	public Restaurant createRestaurant(String name, String location) {
		Restaurant restaurant = new Restaurant(name, location); 
		restaurant = restaurantRepository.save(restaurant);
		DomainEvent event = new RestaurantCreatedEvent(restaurant.getId(), restaurant.getName(), restaurant.getLocation());
		restaurantEventPublisher.publish(event);
		return restaurant; 
	}

 	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		return restaurant;
	}

 	public Restaurant getRestaurantByName(String name) {
		Restaurant restaurant = restaurantRepository.findByName(name);
		return restaurant;
	}
	
	public Collection<Restaurant> getAllRestaurants() {
		Collection<Restaurant> restaurants = restaurantRepository.findAll(); 
		return restaurants;
	}
	
	public Collection<Restaurant> getAllRestaurantsByLocation(String location) {
		Collection<Restaurant> restaurants = restaurantRepository.findAllByLocation(location);
		return restaurants;
	}	

}

