package asw.efood.restaurantservice.web;

import asw.efood.restaurantservice.domain.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import org.springframework.http.MediaType; 
import static org.hamcrest.CoreMatchers.containsString; 

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import org.springframework.test.web.servlet.MvcResult;
import java.util.*; 

/* 
 * Test aggiuntivi relativi ai ristoranti con menu.
 */ 

@WebMvcTest(RestaurantWebController.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantWebControllerMenuTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean 
	private RestaurantService restaurantService; 

	private static final String ANY = "(.|\\s)";
	private static final String ANY_STAR = ANY + "*";

	@Test
	public void testGetRestaurantMenuForRestaurantWithoutMenu() throws Exception {
		// Arrange
		Long restaurantId = 102L; 
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 
		List<MenuItem> menuItems = new ArrayList<>(); 
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);
		when(restaurantService.getRestaurantMenu(restaurantId)).thenReturn(mockMenu);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/{restaurantId}/menu", restaurantId)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant-menu"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", mockRestaurant)) 
			.andExpect(MockMvcResultMatchers.model().attributeExists("menu"))
			.andExpect(MockMvcResultMatchers.model().attribute("menu", mockMenu)) 
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
		verify(restaurantService).getRestaurantMenu(restaurantId);
	}

	@Test
	public void testGetRestaurantMenu() throws Exception {
		// Arrange
		Long restaurantId = 102L; 
		List<MenuItem> menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 14.0) );
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);
		when(restaurantService.getRestaurantMenu(restaurantId)).thenReturn(mockMenu);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/{restaurantId}/menu", restaurantId)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant-menu"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", mockRestaurant)) 
			.andExpect(MockMvcResultMatchers.model().attributeExists("menu"))
			.andExpect(MockMvcResultMatchers.model().attribute("menu", mockMenu)) 
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
		verify(restaurantService).getRestaurantMenu(restaurantId);
	}

	@Test
	public void testCreateOrUpdateRestaurantMenu() throws Exception {
		// Arrange
		Long restaurantId = 102L; 
		List<MenuItem> menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		RestaurantMenu mockMenu = new RestaurantMenu(restaurantId, menuItems);
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);
		when(restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems)).thenReturn(mockMenu);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/web/restaurants/{restaurantId}/menu", restaurantId)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("menuItems[0].id", "CAR")
			.param("menuItems[0].name", "Carbonara")
			.param("menuItems[0].price", "15.0")
			.param("menuItems[1].id", "GRI")
			.param("menuItems[1].name", "Gricia")
			.param("menuItems[1].price", "14.0")
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", mockRestaurant)) 
			.andExpect(MockMvcResultMatchers.model().attributeExists("menu"))
			.andExpect(MockMvcResultMatchers.model().attribute("menu", mockMenu)) 
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
		verify(restaurantService).createOrUpdateRestaurantMenu(restaurantId, menuItems);
	}

}