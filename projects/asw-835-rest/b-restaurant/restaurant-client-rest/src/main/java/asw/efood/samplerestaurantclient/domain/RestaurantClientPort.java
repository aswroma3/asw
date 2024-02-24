package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public interface RestaurantClientPort {
    Long createRestaurant(String name, String location);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(Long restaurantId);
}
