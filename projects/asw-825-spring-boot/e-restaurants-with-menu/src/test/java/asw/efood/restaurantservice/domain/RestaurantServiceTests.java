package asw.efood.restaurantservice.domain;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.Optional;
import java.util.*; 

import org.springframework.dao.DataIntegrityViolationException;

/* 
 * Ancora test unitari, basati su JUnit e i Mock di Mockito
 * 
 * Mock is mainly used in unit tests where the Spring context is not needed or not loaded.
 * See https://medium.com/@ykods/difference-between-mock-and-mockbean-in-spring-testing-9576eb312cdb
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTests {

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private RestaurantMenuRepository restaurantMenuRepository;

	@InjectMocks 
	private RestaurantService restaurantService = new RestaurantServiceImpl(); 

	@Test
	public void testCreateRestaurant() {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantRepository.save(any(Restaurant.class))).thenAnswer(
				r -> {
					Restaurant result = r.getArgument(0); 
					result.setId(restaurantId); 
					return result; 
				}
			);
		when(restaurantMenuRepository.save(any(RestaurantMenu.class))).thenAnswer(
				r -> {
					RestaurantMenu result = r.getArgument(0); 
					return result; 
				}
			);

		// Act
		Restaurant result = restaurantService.createRestaurant("Seta", "Milano");

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		// oppure così (verifica gli oggetti campo a campo) 
		assertEquals(newRestaurant, result);
		
		// Mock Verification 
		verify(restaurantRepository).save(any(Restaurant.class));
		verify(restaurantMenuRepository).save(any(RestaurantMenu.class));
		// questo invece non passa, perché all'oggetto passato come parametro 
		// poi è stato assegnato un id (mockito ricorda il riferimento 
		// al parametro, non ne tiene una copia) 
		// verify(restaurantRepository).save(new Restaurant("Seta", "Milano"));
	}

	@Test
	public void testFailToCreateRestaurant() {
		// Arrange

		// Mock 
		when(restaurantRepository.save(any(Restaurant.class))).thenThrow(DataIntegrityViolationException.class);

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantService.createRestaurant("Seta", "Milano"); }); 
		
		// Mock Verification 
		verify(restaurantRepository).save(any(Restaurant.class));
	}

	@Test
	public void testGetRestaurantById() {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(mockRestaurant));

		// Act
		Restaurant result = restaurantService.getRestaurant(restaurantId);

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		
		// Mock Verification 
		verify(restaurantRepository).findById(restaurantId);
	}

	@Test
	public void testFailToGetRestaurantById() {
		// Arrange
		Long restaurantId = 99L; 

		// Mock 
		when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantService.getRestaurant(restaurantId); }); 

		// Assert

		// Mock Verification 
		verify(restaurantRepository).findById(restaurantId);
	}

	@Test
	public void testGetAllRestaurants() {
		// Arrange
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant("Seta", "Milano"); 
		mr1.setId(1L); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant("L'Omo", "Roma"); 
		mr2.setId(2L); 
		mockRestaurants.add(mr2); 
		Restaurant mr3 = new Restaurant("Baffetto", "Roma"); 
		mr3.setId(3L); 
		mockRestaurants.add(mr3); 

		// Mock 
		when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

		// Act
		Collection<Restaurant> result = restaurantService.getAllRestaurants();

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		assertTrue(result.contains(mr3));
		
		// Mock Verification 
		verify(restaurantRepository).findAll();
	}

	@Test
	public void testGetAllRestaurantsWithNoRestaurants() {
		// Arrange
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock 
		when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

		// Act
		Collection<Restaurant> result = restaurantService.getAllRestaurants();

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());
		
		// Mock Verification 
		verify(restaurantRepository).findAll();
	}

	@Test
	public void testGetRestaurantByNameAndLocation() {
		// Arrange
		String restaurantName = "Seta"; 
		String restaurantLocation = "Milano"; 
		Restaurant mockRestaurant = new Restaurant(restaurantName, restaurantLocation); 
		mockRestaurant.setId(42L); 

		// Mock 
		when(restaurantRepository.findByNameAndLocation(restaurantName, restaurantLocation)).thenReturn(Optional.of(mockRestaurant));

		// Act
		Restaurant result = restaurantService.getRestaurant(restaurantName, restaurantLocation);

		// Assert
		assertNotNull(result);
		assertEquals(restaurantName, result.getName());
		assertEquals(restaurantLocation, result.getLocation());
		assertEquals(42L, result.getId());
		
		// Mock Verification 
		verify(restaurantRepository).findByNameAndLocation(restaurantName, restaurantLocation);
	}

	@Test
	public void testFailToGetRestaurantByNameAndLocation() {
		// Arrange
		String restaurantName = "Seat"; 
		String restaurantLocation = "Milano"; 

		// Mock 
		when(restaurantRepository.findByNameAndLocation(restaurantName, restaurantLocation)).thenReturn(Optional.empty());

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantService.getRestaurant(restaurantName, restaurantLocation); }); 

		// Assert
		
		// Mock Verification 
		verify(restaurantRepository).findByNameAndLocation(restaurantName, restaurantLocation);
	}

	@Test
	public void testGetRestaurantsByLocation() {
		// Arrange
		String restaurantLocation = "Roma"; 
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant("L'Omo", restaurantLocation); 
		mr1.setId(1L); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant("Baffetto", restaurantLocation); 
		mr2.setId(7L); 
		mockRestaurants.add(mr2); 

		// Mock 
		when(restaurantRepository.findAllByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act
		Collection<Restaurant> result = restaurantService.getRestaurantsByLocation(restaurantLocation);

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		
		// Mock Verification 
		verify(restaurantRepository).findAllByLocation(restaurantLocation);
	}

	@Test
	public void testGetRestaurantsByLocationWithNoRestaurants() {
		// Arrange
		String restaurantLocation = "Mialno"; 
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock 
		when(restaurantRepository.findAllByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act
		Collection<Restaurant> result = restaurantService.getRestaurantsByLocation(restaurantLocation);

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());
		
		// Mock Verification 
		verify(restaurantRepository).findAllByLocation(restaurantLocation);
	}

}

