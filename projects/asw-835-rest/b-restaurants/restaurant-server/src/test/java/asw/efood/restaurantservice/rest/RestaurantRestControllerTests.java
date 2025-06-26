package asw.efood.restaurantservice.rest;

import asw.efood.restaurantservice.domain.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import org.springframework.http.MediaType; 

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;

//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;

import java.util.*; 

/* 
 * Test di integrazione per il controller REST. 
 * Viene testata l'API REST/JSON mediante MockMvc.  
 */ 

@WebMvcTest(RestaurantRestController.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean 
	private RestaurantService restaurantService; 
	
	@Test
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenReturn(newRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/rest/restaurants")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"name\": \"Seta\", \"location\": \"Milano\" }")) 
			.andExpect(MockMvcResultMatchers.status().isCreated()) 
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurantId").value(restaurantId))
			;

		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	public void testFailToCreateRestaurant() throws Exception {
		// Arrange

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/rest/restaurants")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"name\": \"Seta\", \"location\": \"Milano\" }")) 
			.andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
			;

		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	public void testGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/{restaurantId}", restaurantId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurantId").value(restaurantId))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Seta"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Milano"))
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
	}

	@Test
	public void testFailToGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 99L; 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/{restaurantId}", restaurantId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
	}

	@Test
	public void testGetAllRestaurants() throws Exception {
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
		when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", hasSize(3)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", 
				hasItem(org.hamcrest.Matchers.hasEntry("name", "Seta")))) 
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants[?(@.restaurantId == 1 && @.name == \"Seta\" && @.location == \"Milano\")]").hasJsonPath())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants[?(@.restaurantId == 2 && @.name == \"L'Omo\" && @.location == \"Roma\")]").hasJsonPath())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants[?(@.restaurantId == 3 && @.name == \"Baffetto\" && @.location == \"Roma\")]").hasJsonPath())
			;

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	public void testGetAllRestaurantsWithNoRestaurants() throws Exception {
		// Arrange
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", hasSize(0)))
			;

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	public void testGetRestaurantByNameAndLocation() throws Exception {
		// Arrange
		String restaurantName = "Seta"; 
		String restaurantLocation = "Milano"; 
		Long restaurantId = 42L; 
		Restaurant mockRestaurant = new Restaurant(restaurantName, restaurantLocation); 
		mockRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantName, restaurantLocation)).thenReturn(mockRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/findByNameAndLocation/")
			.queryParam("name", restaurantName)
			.queryParam("location", restaurantLocation)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurantId").value(restaurantId))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Seta"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Milano"))
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	public void testFailToGetRestaurantByNameAndLocation() throws Exception {
		// Arrange
		String restaurantName = "Seat"; 
		String restaurantLocation = "Milano"; 

		// Mock 
		when(restaurantService.getRestaurant(restaurantName, restaurantLocation)).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/findByNameAndLocation/")
			.queryParam("name", restaurantName)
			.queryParam("location", restaurantLocation)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
			;

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	public void testGetRestaurantsByLocation() throws Exception {
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
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/findByLocation/")
			.queryParam("location", restaurantLocation)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", hasSize(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", 
				hasItem(org.hamcrest.Matchers.hasEntry("name", "L'Omo")))) 
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants[?(@.restaurantId == 1 && @.name == \"L'Omo\" && @.location == \"Roma\")]").hasJsonPath())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants[?(@.restaurantId == 7 && @.name == \"Baffetto\" && @.location == \"Roma\")]").hasJsonPath())
			;

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

	@Test
	public void testGetRestaurantsByLocationWithNoRestaurants() throws Exception {
		// Arrange
		String restaurantLocation = "Mialno"; 
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/restaurants/findByLocation/")
			.queryParam("location", restaurantLocation)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.restaurants", hasSize(0)))
			;

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

}