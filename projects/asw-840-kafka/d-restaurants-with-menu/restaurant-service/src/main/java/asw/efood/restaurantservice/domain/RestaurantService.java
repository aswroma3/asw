package asw.efood.restaurantservice.domain;

import java.util.*; 

public interface RestaurantService {

	Restaurant createRestaurant(String name, String location) throws RestaurantServiceException; 

	Restaurant getRestaurant(Long id) throws RestaurantServiceException; 

	Restaurant getRestaurant(String name, String location) throws RestaurantServiceException; 

	Collection<Restaurant> getAllRestaurants(); 
	
	Collection<Restaurant> getRestaurantsByLocation(String location); 

	RestaurantMenu createOrUpdateRestaurantMenu(Long id, List<MenuItem> menuItems) throws RestaurantServiceException; 
	
//	RestaurantMenu createOrUpdateRestaurantMenu(String name, String location, List<MenuItem> menuItems) throws RestaurantServiceException; 

	RestaurantMenu getRestaurantMenu(Long id) throws RestaurantServiceException; 

}

