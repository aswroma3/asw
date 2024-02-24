package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public interface RestaurantClientPort {
    Long createRestaurant(String name, String location);
    Restaurant getRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurants();
}
