package asw.efood.samplerestaurantclient.restaurantclient.rest;

import asw.efood.samplerestaurantclient.domain.*;
import asw.efood.restaurantservice.api.rest.*; 

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import java.util.*; 

import java.util.concurrent.CompletableFuture;

/* 
 * Test di unitari per il client REST. 
 * Viene testata l'API asincrona.  
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantClientAsyncRestAdapterTests {

	@Mock 
	private RestaurantClientPort restaurantClientAdapter; 

	@InjectMocks 
	private RestaurantClientAsyncRestAdapter restaurantClientAsyncRestAdapter;

	@Test
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 

		// Mock 
		when(restaurantClientAdapter.createRestaurant("Seta", "Milano")).thenReturn(restaurantId);

		// Act
		CompletableFuture<Long> futureResult = restaurantClientAsyncRestAdapter.createRestaurantAsync("Seta", "Milano"); 

		// Assert
		assertNotNull(futureResult);
		Long result = futureResult.get(); 
		assertNotNull(result);
		assertEquals(restaurantId, result);
		
		// Mock Verification 
		verify(restaurantClientAdapter).createRestaurant("Seta", "Milano");
	}

	@Test
	public void testFailToCreateRestaurant() throws Exception {
		// Arrange

		// Mock 
		when(restaurantClientAdapter.createRestaurant("Seta", "Milano")).thenThrow(RestaurantServiceException.class);

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientAsyncRestAdapter.createRestaurantAsync("Seta", "Milano"); }); 
		
		// Mock Verification 
		verify(restaurantClientAdapter).createRestaurant("Seta", "Milano");
	}

	@Test
	public void testGetRestaurantByIdAsync() throws Exception {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant mockRestaurant = new Restaurant(restaurantId, "Seta", "Milano"); 

		// Mock 
		when(restaurantClientAdapter.getRestaurant(restaurantId)).thenReturn(mockRestaurant);

		// Act
		CompletableFuture<Restaurant> futureResult = restaurantClientAsyncRestAdapter.getRestaurantAsync(restaurantId); 

		// Assert
		assertNotNull(futureResult);
		Restaurant result = futureResult.get(); 
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		
		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurant(restaurantId);
	}

	@Test
	public void testFailToGetRestaurantByIdAsync() throws Exception {
		// Arrange
		Long restaurantId = 99L; 

		// Mock 
		when(restaurantClientAdapter.getRestaurant(restaurantId)).thenThrow(RestaurantServiceException.class);

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientAsyncRestAdapter.getRestaurantAsync(restaurantId); }); 

		// Assert

		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurant(restaurantId);
	}

	@Test
	public void testGetAllRestaurantsAsync() throws Exception {
		// Arrange
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "Seta", "Milano"); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(2L, "L'Omo", "Roma"); 
		mockRestaurants.add(mr2); 
		Restaurant mr3 = new Restaurant(3L, "Baffetto", "Roma"); 
		mockRestaurants.add(mr3); 

		// Mock 
		when(restaurantClientAdapter.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act
		CompletableFuture<List<Restaurant>> futureResult = restaurantClientAsyncRestAdapter.getAllRestaurantsAsync(); 

		// Assert
		assertNotNull(futureResult);
		Collection<Restaurant> result = futureResult.get();
		assertEquals(3, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		assertTrue(result.contains(mr3));
		
		// Mock Verification 
		verify(restaurantClientAdapter).getAllRestaurants();
	}

	@Test
	public void testGetAllRestaurantsAsyncWithNoRestaurants() throws Exception {
		// Arrange
		List<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock 
		when(restaurantClientAdapter.getAllRestaurants()).thenReturn(mockRestaurants);


		// Act
		CompletableFuture<List<Restaurant>> futureResult = restaurantClientAsyncRestAdapter.getAllRestaurantsAsync(); 

		// Assert
		assertNotNull(futureResult);
		Collection<Restaurant> result = futureResult.get();
		assertEquals(0, result.size());
		
		// Mock Verification 
		verify(restaurantClientAdapter).getAllRestaurants();
	}

	@Test
	public void testGetRestaurantByNameAndLocationAsync() throws Exception {
		// Arrange
		String restaurantName = "Seta"; 
		String restaurantLocation = "Milano"; 
		Long restaurantId = 42L;
		Restaurant mockRestaurant = new Restaurant(restaurantId, restaurantName, restaurantLocation); 
		Restaurant expectedRestaurant = new Restaurant(restaurantId, restaurantName, restaurantLocation); 

		// Mock 
		when(restaurantClientAdapter.getRestaurantByNameAndLocation("Seta", "Milano")).thenReturn(mockRestaurant);

		// Act
		CompletableFuture<Restaurant> futureResult = restaurantClientAsyncRestAdapter.getRestaurantByNameAndLocationAsync("Seta", "Milano"); 

		// Assert
		assertNotNull(futureResult);
		Restaurant result = futureResult.get(); 
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		// oppure 
		assertEquals(expectedRestaurant, result);
		
		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurantByNameAndLocation("Seta", "Milano");
	}

	@Test
	public void testFailToGetRestaurantByNameAndLocationAsync() throws Exception {
		// Arrange
		String restaurantName = "Seat"; 
		String restaurantLocation = "Milano"; 

		// Mock 
		when(restaurantClientAdapter.getRestaurantByNameAndLocation(restaurantName, restaurantLocation)).thenThrow(RestaurantServiceException.class);

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientAsyncRestAdapter.getRestaurantByNameAndLocationAsync(restaurantName, restaurantLocation); }); 

		// Assert

		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurantByNameAndLocation(restaurantName, restaurantLocation);
	}

	@Test
	public void testGetAllRestaurantsByLocationAsync() throws Exception {
		// Arrange
		String restaurantLocation = "Roma"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "L'Omo", restaurantLocation); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(7L, "Baffetto", restaurantLocation); 
		mockRestaurants.add(mr2); 
		
		// Mock 
		when(restaurantClientAdapter.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act
		CompletableFuture<List<Restaurant>> futureResult = restaurantClientAsyncRestAdapter.getRestaurantsByLocationAsync(restaurantLocation); 

		// Assert
		assertNotNull(futureResult);
		Collection<Restaurant> result = futureResult.get();
		assertEquals(2, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		
		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurantsByLocation(restaurantLocation);
	}

	@Test
	public void testGetAllRestaurantsByLocationAsyncWithNoRestaurants() throws Exception {
		// Arrange
		String restaurantLocation = "Mialno"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		
		// Mock 
		when(restaurantClientAdapter.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act
		CompletableFuture<List<Restaurant>> futureResult = restaurantClientAsyncRestAdapter.getRestaurantsByLocationAsync(restaurantLocation); 

		// Assert
		assertNotNull(futureResult);
		Collection<Restaurant> result = futureResult.get();
		assertEquals(0, result.size());
		
		// Mock Verification 
		verify(restaurantClientAdapter).getRestaurantsByLocation(restaurantLocation);
	}

}