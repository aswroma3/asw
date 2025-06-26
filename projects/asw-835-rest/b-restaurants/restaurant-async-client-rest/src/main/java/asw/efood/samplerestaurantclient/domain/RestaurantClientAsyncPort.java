package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

import java.util.concurrent.CompletableFuture;

public interface RestaurantClientAsyncPort {
    CompletableFuture<Long> createRestaurantAsync(String name, String location) throws RestaurantServiceException;
    CompletableFuture<Restaurant> getRestaurantAsync(Long restaurantId) throws RestaurantServiceException;
    CompletableFuture<List<Restaurant>> getAllRestaurantsAsync();
    CompletableFuture<Restaurant> getRestaurantByNameAndLocationAsync(String name, String location) throws RestaurantServiceException;
    CompletableFuture<List<Restaurant>> getRestaurantsByLocationAsync(String location);
}
