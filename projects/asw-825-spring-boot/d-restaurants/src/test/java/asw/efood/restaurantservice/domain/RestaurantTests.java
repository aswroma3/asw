package asw.efood.restaurantservice.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension; 

/* 
 * Semplici test unitari, basati solo su JUnit. 
 */ 

@ExtendWith(SpringExtension.class)
public class RestaurantTests {

	@Test
	public void testRestaurant() {
		// Arrange
		Restaurant restaurant = new Restaurant("Seta", "Milano"); 

		// Act

		// Assert
		assertEquals("Seta", restaurant.getName());
		assertEquals("Milano", restaurant.getLocation());
		assertEquals(null, restaurant.getId());
	}

	@Test
	public void testRestaurantEqualsIsOnAllAttributes() {
		// Arrange
		Restaurant r1 = new Restaurant("Seta", "Milano"); 
		r1.setId(1L); 
		Restaurant r2 = new Restaurant("Seta", "Milano"); 
		r2.setId(1L); 

		// Act

		// Assert
		assertEquals(r1, r2);
	}

	@Test
	public void testRestaurantEqualsIsNotOnlyOnTheId() {
		// Arrange
		Restaurant r1 = new Restaurant("Seta", "Milano"); 
		r1.setId(1L); 
		Restaurant r2 = new Restaurant("L'Omo", "Roma"); 
		r2.setId(1L); 

		// Act

		// Assert
		assertNotEquals(r1, r2);
	}

}

