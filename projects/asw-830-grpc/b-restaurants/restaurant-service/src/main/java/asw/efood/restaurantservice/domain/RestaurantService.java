package asw.efood.restaurantservice.domain;

import java.util.*; 

public interface RestaurantService {

	Restaurant createRestaurant(String name, String location) throws RestaurantServiceException; 

	Restaurant getRestaurant(Long id) throws RestaurantServiceException; 

	Restaurant getRestaurant(String name, String location) throws RestaurantServiceException; 

	Collection<Restaurant> getAllRestaurants(); 
	
	Collection<Restaurant> getRestaurantsByLocation(String location); 

}

