package asw.efood.restaurantservice.domain;

import java.util.*; 

public interface RestaurantService {

	public Restaurant createRestaurant(String name, String location) throws RestaurantServiceException; 

	public Restaurant getRestaurant(Long id) throws RestaurantServiceException; 

	public Restaurant getRestaurant(String name, String location) throws RestaurantServiceException; 

	public Collection<Restaurant> getAllRestaurants(); 
	
	public Collection<Restaurant> getRestaurantsByLocation(String location); 

	public RestaurantMenu createOrUpdateRestaurantMenu(Long id, List<MenuItem> menuItems) throws RestaurantServiceException; 

	public RestaurantMenu getRestaurantMenu(Long id) throws RestaurantServiceException; 

}

