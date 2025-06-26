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
 * Test aggiuntivi relativi ai ristoranti con menu.
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceMenuTests {

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private RestaurantMenuRepository restaurantMenuRepository;

	@InjectMocks 
	private RestaurantService restaurantService; 

	@Test
	public void testCreateRestaurant() {
		// Arrange
		Long restaurantId = 102L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		Restaurant expectedRestaurant = new Restaurant("Seta", "Milano"); 
		expectedRestaurant.setId(restaurantId); 

		RestaurantMenu expectedMenu = new RestaurantMenu(restaurantId);

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
		assertEquals(expectedRestaurant, result);
		
		// Mock Verification 
		verify(restaurantRepository).save(expectedRestaurant);
		verify(restaurantMenuRepository).save(expectedMenu);
	}

	@Test
	public void testGetRestaurantMenuForRestaurantWithoutMenu() {
		// Arrange
		Long restaurantId = 102L; 
		List<MenuItem> menuItems = new ArrayList<>(); 
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);

		// Mock 
		when(restaurantMenuRepository.findById(restaurantId)).thenReturn(Optional.of(mockMenu));

		// Act
		RestaurantMenu result = restaurantService.getRestaurantMenu(restaurantId);

		// Assert
		assertNotNull(result);
		assertEquals(mockMenu, result);
		assertEquals(menuItems, result.getMenuItems());
		assertEquals(0, result.getMenuItems().size());
		
		// Mock Verification 
		verify(restaurantMenuRepository).findById(restaurantId);
	}

	@Test
	public void testGetRestaurantMenu() {
		// Arrange
		Long restaurantId = 1L; 
		List<MenuItem> menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 14.0) );
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);

		// Mock 
		when(restaurantMenuRepository.findById(restaurantId)).thenReturn(Optional.of(mockMenu));

		// Act
		RestaurantMenu result = restaurantService.getRestaurantMenu(restaurantId);

		// Assert
		assertNotNull(result);
		assertEquals(mockMenu, result);
		assertEquals(menuItems, result.getMenuItems());
		
		// Mock Verification 
		verify(restaurantMenuRepository).findById(restaurantId);
	}

	@Test
	public void testCreateOrUpdateRestaurantMenu() {
		// Arrange
		Long restaurantId = 102L; 
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId);
		
		List<MenuItem> menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 14.0) );
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);

		// Mock 
		when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(mockRestaurant));
		when(restaurantMenuRepository.save(mockMenu)).thenReturn(mockMenu);

		// Act
		RestaurantMenu result = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);

		// Assert
		assertNotNull(result);
		assertEquals(mockMenu, result);
		
		// Mock Verification 
		verify(restaurantRepository).findById(restaurantId);
		verify(restaurantMenuRepository).save(mockMenu);
	}

}

