package asw.efood.restaurantservice.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension; 

import java.util.*; 

/* 
 * Semplici test unitari, basati solo su JUnit. 
 */ 

@ExtendWith(SpringExtension.class)
public class RestaurantMenuTests {

	@Test
	public void testRestaurantMenu() {
		// Arrange
		Long restaurantId = 42L; 
		List<MenuItem> menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 14.0) );

		// Act
		RestaurantMenu menu = new RestaurantMenu(restaurantId, menuItems);

		// Assert
		assertEquals(restaurantId, menu.getId());
		assertEquals(menuItems, menu.getMenuItems()); 
		assertEquals(3, menu.getMenuItems().size()); 
		assertTrue(menu.getMenuItems().contains(new MenuItem("CAR", "Carbonara", 15.0))); 
	}

	@Test
	public void testEmptyRestaurantMenu() {
		// Arrange
		Long restaurantId = 42L; 
		List<MenuItem> expectedMenuItems = new ArrayList<>(); 
		RestaurantMenu expectedMenu = new RestaurantMenu(restaurantId, expectedMenuItems);

		// Act
		RestaurantMenu menu = new RestaurantMenu(restaurantId);

		// Assert
		assertEquals(expectedMenu, menu);
		assertEquals(restaurantId, menu.getId());
		assertEquals(expectedMenuItems, menu.getMenuItems()); 
		assertEquals(0, menu.getMenuItems().size()); 
	}

}

