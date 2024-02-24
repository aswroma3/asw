package asw.efood.restaurantservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

 	public Restaurant createRestaurant(String name, String location) {
		Restaurant restaurant = new Restaurant(name, location); 
		restaurant = restaurantRepository.save(restaurant);
		return restaurant;
	}

 	public Restaurant createRestaurantWithMenu(String name, String location, List<MenuItem> menuItems) {
		RestaurantMenu menu = new RestaurantMenu(menuItems);
		Restaurant restaurant = new Restaurant(name, location, menu); 
		restaurant = restaurantRepository.save(restaurant);
		return restaurant;
	}

 	public Restaurant createOrUpdateRestaurantMenu(Long id, List<MenuItem> menuItems) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		RestaurantMenu menu = new RestaurantMenu(menuItems);
		restaurant.setMenu(menu); 
		restaurant = restaurantRepository.save(restaurant);
		return restaurant;
	}
	
 	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		return restaurant;
	}

 	public RestaurantMenu getRestaurantMenu(Long id) {
		Restaurant restaurant = restaurantRepository.findByIdWithMenu(id);
		RestaurantMenu menu = restaurant.getMenu();
		return menu; 
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

