package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public interface RestaurantClientPort {
    Long createRestaurant(String name, String location);
    Long createRestaurantMenu(Long restaurantId, List<MenuItem> menuItems);
    Restaurant getRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurants();
    List<MenuItem> getRestaurantMenu(Long restaurantId);
}
