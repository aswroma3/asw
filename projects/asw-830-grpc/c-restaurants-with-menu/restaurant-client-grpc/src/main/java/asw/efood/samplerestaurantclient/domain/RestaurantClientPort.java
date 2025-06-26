package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public interface RestaurantClientPort {
    Long createRestaurant(String name, String location) throws RestaurantServiceException;
    Restaurant getRestaurant(Long restaurantId) throws RestaurantServiceException;
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantByNameAndLocation(String name, String location) throws RestaurantServiceException;
    List<Restaurant> getRestaurantsByLocation(String location);

    Long createRestaurantMenu(Long restaurantId, List<MenuItem> menuItems) throws RestaurantServiceException;
    List<MenuItem> getRestaurantMenu(Long restaurantId) throws RestaurantServiceException;
}
