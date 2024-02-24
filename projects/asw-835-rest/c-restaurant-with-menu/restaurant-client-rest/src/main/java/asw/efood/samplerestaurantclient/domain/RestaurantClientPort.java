package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public interface RestaurantClientPort {
    Long createRestaurant(String name, String location);
    Long createRestaurantMenu(Long restaurantId, List<MenuItem> menuItems);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(Long restaurantId);
    List<MenuItem> getRestaurantMenu(Long restaurantId);
}
