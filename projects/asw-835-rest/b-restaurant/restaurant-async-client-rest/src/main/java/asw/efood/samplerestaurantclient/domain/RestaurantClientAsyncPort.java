package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

import java.util.concurrent.CompletableFuture;

public interface RestaurantClientAsyncPort {
    CompletableFuture<Long> createRestaurantAsync(String name, String location);
    CompletableFuture<List<Restaurant>> getAllRestaurantsAsync();
    CompletableFuture<Restaurant> getRestaurantAsync(Long restaurantId);
}
