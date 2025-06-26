package asw.efood.samplerestaurantclient.restaurantclient.rest;

import asw.efood.samplerestaurantclient.domain.*;
import asw.efood.restaurantservice.api.rest.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import org.springframework.http.HttpStatus;

import java.io.IOException;

import java.util.*; 

/* 
 * Test di integrazione per il client REST. 
 * Viene testata l'API REST/JSON.  
 */ 

@SpringBootTest 
@ExtendWith(MockitoExtension.class)
public class RestaurantClientRestAdapterTests {

	private static MockWebServer mockWebServer;

	@MockitoBean 
	/* disabilita l'esecuzione di quest runner */ 
	private RestaurantClientRunner runner; 

	@Autowired 
	private RestaurantClientPort restaurantClientPort;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s/rest", mockWebServer.getPort());
    }
	
	@Test
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant(restaurantId, "Seta", "Milano"); 
		
		String mockRequest = "{\"name\":\"Seta\",\"location\":\"Milano\"}"; 
		String mockResponse = "{ \"restaurantId\": 101 }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.CREATED.value()));

		// Act 
		Long result = restaurantClientPort.createRestaurant("Seta", "Milano"); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result);

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/rest/restaurants", recordedRequest.getPath());
		// questo test mi sembra un po' fragile 
        assertEquals(mockRequest, recordedRequest.getBody().readUtf8());
	}

	@Test
	public void testFailToCreateRestaurant() throws Exception {
		// Arrange
		String mockRequest = "{\"name\":\"Seta\",\"location\":\"Milano\"}"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
				.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value()));

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientPort.createRestaurant("Seta", "Milano"); }); 

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/rest/restaurants", recordedRequest.getPath());
		// questo test mi sembra un po' fragile 
        assertEquals(mockRequest, recordedRequest.getBody().readUtf8());
	}

	@Test
	public void testGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant restaurant = new Restaurant(restaurantId, "Seta", "Milano"); 
		
		String mockResponse = "{ \"restaurantId\": 1, \"name\": \"Seta\", \"location\": \"Milano\" }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Restaurant result = restaurantClientPort.getRestaurant(restaurantId); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		// oppure così (verifica gli oggetti campo a campo) 
		assertEquals(restaurant, result);

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/1", recordedRequest.getPath());
	}

	@Test
	public void testFailToGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 99L; 
		
		// Mock 
        mockWebServer.enqueue(new MockResponse()
				.setResponseCode(HttpStatus.NOT_FOUND.value()));

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientPort.getRestaurant(restaurantId); }); 

		// Assert

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/99", recordedRequest.getPath());
	}

	@Test
	public void testGetAllRestaurants() throws Exception {
		// Arrange
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "Seta", "Milano"); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(2L, "L'Omo", "Roma"); 
		mockRestaurants.add(mr2); 
		Restaurant mr3 = new Restaurant(3L, "Baffetto", "Roma"); 
		mockRestaurants.add(mr3); 
		
		String mockResponse = "{ \"restaurants\": [ { \"restaurantId\": 1, \"name\": \"Seta\", \"location\": \"Milano\" }, { \"restaurantId\": 2, \"name\": \"L'Omo\", \"location\": \"Roma\" }, { \"restaurantId\": 3, \"name\": \"Baffetto\", \"location\": \"Roma\" } ] }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getAllRestaurants(); 

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		assertTrue(result.contains(mr3));
		// oppure così (verifica gli oggetti campo a campo) 
//		assertEquals(restaurant, result);

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants", recordedRequest.getPath());
	}

	@Test
	public void testGetAllRestaurantsWithNoRestaurants() throws Exception {
		// Arrange
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		
		String mockResponse = "{ \"restaurants\": [ ] }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getAllRestaurants(); 

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants", recordedRequest.getPath());
	}

	@Test
	public void testGetRestaurantByNameAndLocation() throws Exception {
		// Arrange
		String restaurantName = "Seta"; 
		String restaurantLocation = "Milano"; 
		Long restaurantId = 42L; 
		Restaurant restaurant = new Restaurant(restaurantId, restaurantName, restaurantLocation); 
		
		String mockResponse = "{ \"restaurantId\": 42, \"name\": \"Seta\", \"location\": \"Milano\" }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Restaurant result = restaurantClientPort.getRestaurantByNameAndLocation(restaurantName, restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals(restaurantName, result.getName());
		assertEquals(restaurantLocation, result.getLocation());
		// oppure così (verifica gli oggetti campo a campo) 
		assertEquals(restaurant, result);

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/findByNameAndLocation/?name=Seta&location=Milano", recordedRequest.getPath());
	}

	@Test
	public void testFailToGetRestaurantByNameAndLocation() throws Exception {
		// Arrange
		String restaurantName = "Seat"; 
		String restaurantLocation = "Milano"; 
		
		// Mock 
        mockWebServer.enqueue(new MockResponse()
				.setResponseCode(HttpStatus.NOT_FOUND.value()));

		// Act and assert 
		assertThrows(RestaurantServiceException.class, () -> { restaurantClientPort.getRestaurantByNameAndLocation(restaurantName, restaurantLocation); }); 


		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/findByNameAndLocation/?name=Seat&location=Milano", recordedRequest.getPath());
	}

	@Test
	public void testGetAllRestaurantsByLocation() throws Exception {
		// Arrange
		String restaurantLocation = "Roma"; 
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "L'Omo", restaurantLocation); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(7L, "Baffetto", restaurantLocation); 
		mockRestaurants.add(mr2); 
		
		String mockResponse = "{ \"restaurants\": [ { \"restaurantId\": 1, \"name\": \"L'Omo\", \"location\": \"Roma\" }, { \"restaurantId\": 7, \"name\": \"Baffetto\", \"location\": \"Roma\" } ] }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getRestaurantsByLocation(restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		// oppure così (verifica gli oggetti campo a campo) 
//		assertEquals(restaurant, result);

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/findByLocation/?location=Roma", recordedRequest.getPath());
	}

	@Test
	public void testGetAllRestaurantsByLocationWithNoRestaurants() throws Exception {
		// Arrange
		String restaurantLocation = "Mialno"; 
		Collection<Restaurant> mockRestaurants = new ArrayList<>(); 
		
		String mockResponse = "{ \"restaurants\": [ ] }"; 

		// Mock 
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
				.setResponseCode(HttpStatus.OK.value()));

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getRestaurantsByLocation(restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());

		// Mock Verification 
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/rest/restaurants/findByLocation/?location=Mialno", recordedRequest.getPath());
	}

}