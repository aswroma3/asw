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

@WebMvcTest(RestaurantWebController.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantWebControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean 
	private RestaurantService restaurantService; 

	private static final String ANY = "(.|\\s)";
	private static final String ANY_STAR = ANY + "*";

	@Test
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 
		Restaurant expectedRestaurant = new Restaurant("Seta", "Milano"); 
		expectedRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenReturn(newRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/web/restaurants")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("name", "Seta")
			.param("location", "Milano")
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isCreated()) 
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", expectedRestaurant)) 
			.andExpect(MockMvcResultMatchers.content().string(containsString("Restaurant <span>Seta</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("id=<span>101</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("in <span>Milano</span>")))
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
		mockMvc.perform(MockMvcRequestBuilders.post("/web/restaurants")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("name", "Seta")
			.param("location", "Milano")
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
			;

		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	public void getRestaurantByIdTest() throws Exception {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant mockRestaurant = new Restaurant("Seta", "Milano"); 
		mockRestaurant.setId(restaurantId); 
		Restaurant expectedRestaurant = new Restaurant("Seta", "Milano"); 
		expectedRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/{restaurantId}", restaurantId)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", expectedRestaurant)) 
			.andExpect(MockMvcResultMatchers.content().string(containsString("Restaurant <span>Seta</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("id=<span>1</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("in <span>Milano</span>")))
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
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/{restaurantId}", restaurantId)
			.accept(MediaType.TEXT_HTML))
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
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants")
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurants"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurants", mockRestaurants)) 
			.andReturn();
		String htmlData = mvcResult.getResponse().getContentAsString();
		assertTrue(htmlData.matches(ANY_STAR + "<td>Seta</td>" + ANY_STAR + "<td>Milano</td>" + ANY_STAR + "<td>1</td>" + ANY_STAR));

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
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants")
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurants"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurants", mockRestaurants)) 
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
		Restaurant expectedRestaurant = new Restaurant(restaurantName, restaurantLocation); 
		expectedRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantName, restaurantLocation)).thenReturn(mockRestaurant);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/findByNameAndLocation/")
			.queryParam("name", restaurantName)
			.queryParam("location", restaurantLocation)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurant"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurant"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurant", expectedRestaurant)) 
			.andExpect(MockMvcResultMatchers.content().string(containsString("Restaurant <span>Seta</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("id=<span>42</span>")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("in <span>Milano</span>")))
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
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/findByNameAndLocation/")
			.queryParam("name", restaurantName)
			.queryParam("location", restaurantLocation)
			.accept(MediaType.TEXT_HTML))
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
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/findByLocation/")
			.queryParam("location", restaurantLocation)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurants-by-location"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurants", mockRestaurants)) 
			.andReturn()
			;

		String htmlData = mvcResult.getResponse().getContentAsString();
		assertTrue(htmlData.matches(ANY_STAR + "<td>Baffetto</td>" + ANY_STAR + "<td>Roma</td>" + ANY_STAR + "<td>7</td>" + ANY_STAR));

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
		mockMvc.perform(MockMvcRequestBuilders.get("/web/restaurants/findByLocation/")
			.queryParam("location", restaurantLocation)
			.accept(MediaType.TEXT_HTML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("get-restaurants-by-location"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
			.andExpect(MockMvcResultMatchers.model().attribute("restaurants", mockRestaurants)) 
			;

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

}