package asw.efood.restaurantservice.grpc;

import asw.efood.restaurantservice.domain.*;

import asw.efood.restaurantservice.init.InitRestaurantDb; 

import asw.efood.restaurantservice.api.grpc.*;

import io.grpc.*; 

import com.google.common.util.concurrent.ListenableFuture;

import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;  

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import java.util.stream.*; 

import java.util.concurrent.ExecutionException; 
import java.util.concurrent.TimeUnit; 

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import io.grpc.inprocess.InProcessServerBuilder; 
import io.grpc.inprocess.InProcessChannelBuilder; 

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/* 
 * Test di integrazione per il controller REST. 
 * Viene testata l'API REST/JSON.  
 */ 

@SpringBootTest(properties = {
        "asw.efood.restaurantservice.grpc.inprocessserver=true", 
		"asw.efood.restaurantservice.grpc.inprocessservername=TestServer" 
        })
@DirtiesContext 
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceGrpcServerTests {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@MockitoBean 
	private RestaurantService restaurantService; 

	@MockitoBean 
	/* disabilita l'esecuzione di questo runner */ 
	private InitRestaurantDb runner; 

	@Value("${asw.efood.restaurantservice.grpc.inprocessservername}")
    private String serverName;

	private ManagedChannel inProcessChannel;
	private RestaurantServiceGrpc.RestaurantServiceFutureStub futureStub;

	@BeforeEach
//	@PostConstruct
    public void init() throws IOException {
        inProcessChannel = InProcessChannelBuilder
            .forName(serverName)
            .directExecutor()
            .build();
        futureStub = RestaurantServiceGrpc.newFutureStub(inProcessChannel);
	}

    @AfterEach
//    @PreDestroy
    public void shutdown() throws InterruptedException {
		inProcessChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

	@Test
	@DirtiesContext 
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenReturn(newRestaurant);

		// Act and Assert
        CreateRestaurantRequest request = CreateRestaurantRequest.newBuilder()
                .setName("Seta")
                .setLocation("Milano")
                .build();
		ListenableFuture<CreateRestaurantReply> futureReply = futureStub.createRestaurant(request);
        CreateRestaurantReply reply = futureReply.get();
		assertNotNull(reply); 
		assertEquals(restaurantId, reply.getRestaurantId()); 
				
		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	@DirtiesContext 
	public void testFailToCreateRestaurant() throws Exception {
		// Arrange
		String restaurantName = "Seta"; 
		String restaurantLocation = "Milano"; 

		// Mock 
		when(restaurantService.createRestaurant(restaurantName, restaurantLocation)).thenThrow(RestaurantServiceException.class);

		// Act and Assert
        CreateRestaurantRequest request = CreateRestaurantRequest.newBuilder()
                .setName(restaurantName)
                .setLocation(restaurantLocation)
                .build();
		Exception e = assertThrows(ExecutionException.class, () -> { 
				ListenableFuture<CreateRestaurantReply> futureReply = futureStub.createRestaurant(request);
				CreateRestaurantReply reply = futureReply.get();
			}); 
//		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: ALREADY_EXISTS"));
		Throwable cause = e.getCause(); 
//		assertEquals(StatusRuntimeException.class, cause.getClass());
		assertTrue(cause instanceof StatusRuntimeException);
		StatusRuntimeException sre = (StatusRuntimeException) cause; 
		assertEquals(Status.Code.ALREADY_EXISTS, sre.getStatus().getCode());
			
		// Mock Verification 
		verify(restaurantService).createRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 1L; 
		Restaurant expectedRestaurant = new Restaurant("Seta", "Milano"); 
		expectedRestaurant.setId(restaurantId); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(expectedRestaurant);

		// Act and Assert
		GetRestaurantByIdRequest request = GetRestaurantByIdRequest.newBuilder().setRestaurantId(restaurantId).build();
		ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantById(request);
		GetRestaurantReply reply = futureReply.get();
		assertNotNull(reply); 
		assertEquals(restaurantId, reply.getRestaurantId()); 
		assertEquals("Seta", reply.getName()); 
		assertEquals("Milano", reply.getLocation()); 
		// oppure 
		Restaurant restaurant = new Restaurant(reply.getName(), reply.getLocation());
		restaurant.setId(reply.getRestaurantId()); 
		assertEquals(expectedRestaurant, restaurant);
				
		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
	}

	@Test
	@DirtiesContext 
	public void testFailToGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 99L; 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		GetRestaurantByIdRequest request = GetRestaurantByIdRequest.newBuilder().setRestaurantId(restaurantId).build();
		Exception e = assertThrows(ExecutionException.class, () -> { 
				ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantById(request);
				GetRestaurantReply reply = futureReply.get();
			}); 
		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: NOT_FOUND"));
				
		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantId);
	}

	@Test
	@DirtiesContext 
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
		GetRestaurantByNameAndLocationRequest request = 
			GetRestaurantByNameAndLocationRequest.newBuilder().setName(restaurantName).setLocation(restaurantLocation).build();
		ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantByNameAndLocation(request);
		GetRestaurantReply reply = futureReply.get();
		assertNotNull(reply); 
		assertEquals(restaurantId, reply.getRestaurantId()); 
		assertEquals("Seta", reply.getName()); 
		assertEquals("Milano", reply.getLocation()); 
		// oppure 
		Restaurant restaurant = new Restaurant(reply.getName(), reply.getLocation());
		restaurant.setId(reply.getRestaurantId()); 
		assertEquals(expectedRestaurant, restaurant);
				
		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testFailToGetRestaurantByNameAndLocation() throws Exception {
		// Arrange
		String restaurantName = "Seat"; 
		String restaurantLocation = "Milano"; 

		// Mock 
		when(restaurantService.getRestaurant(restaurantName, restaurantLocation)).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		GetRestaurantByNameAndLocationRequest request = 
			GetRestaurantByNameAndLocationRequest.newBuilder().setName(restaurantName).setLocation(restaurantLocation).build();
		Exception e = assertThrows(ExecutionException.class, () -> { 
				ListenableFuture<GetRestaurantReply> futureReply = futureStub.getRestaurantByNameAndLocation(request);
				GetRestaurantReply reply = futureReply.get();
			}); 
		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: NOT_FOUND"));
				
		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testGetAllRestaurants() throws Exception {
		// Arrange
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
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
		GetAllRestaurantsRequest request = GetAllRestaurantsRequest.newBuilder().build();
		ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getAllRestaurants(request);
        GetRestaurantsReply reply = futureReply.get();
		assertNotNull(reply); 
		List<Restaurant> restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> { 
							Restaurant r = new Restaurant(restaurant.getName(), restaurant.getLocation()); 
							r.setId(restaurant.getRestaurantId());
							return r;
						}
					)
					.collect(Collectors.toList()); 
		assertEquals(restaurants, mockRestaurants); 

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	@DirtiesContext 
	public void testGetAllRestaurantsWithNoRestaurants() throws Exception {
		// Arrange
		List<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act and Assert
		GetAllRestaurantsRequest request = GetAllRestaurantsRequest.newBuilder().build();
		ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getAllRestaurants(request);
        GetRestaurantsReply reply = futureReply.get();
		assertNotNull(reply); 
		List<Restaurant> restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> { 
							Restaurant r = new Restaurant(restaurant.getName(), restaurant.getLocation()); 
							r.setId(restaurant.getRestaurantId());
							return r;
						}
					)
					.collect(Collectors.toList()); 
		assertEquals(restaurants, mockRestaurants); 

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantsByLocation() throws Exception {
		// Arrange
		String restaurantLocation = "Roma"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant("L'Omo", restaurantLocation); 
		mr1.setId(1L); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant("Baffetto", restaurantLocation); 
		mr2.setId(7L); 
		mockRestaurants.add(mr2); 

		// Mock  
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act and Assert
		GetRestaurantsByLocationRequest request = GetRestaurantsByLocationRequest.newBuilder().setLocation(restaurantLocation).build();
		ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getRestaurantsByLocation(request);
        GetRestaurantsReply reply = futureReply.get();
		assertNotNull(reply); 
		List<Restaurant> restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> { 
							Restaurant r = new Restaurant(restaurant.getName(), restaurant.getLocation()); 
							r.setId(restaurant.getRestaurantId());
							return r;
						}
					)
					.collect(Collectors.toList()); 
		assertEquals(restaurants, mockRestaurants); 

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantsByLocationWithNoRestaurants() throws Exception {
		// Arrange
		String restaurantLocation = "Mialno"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act and Assert
		GetRestaurantsByLocationRequest request = GetRestaurantsByLocationRequest.newBuilder().setLocation(restaurantLocation).build();
		ListenableFuture<GetRestaurantsReply> futureReply = futureStub.getRestaurantsByLocation(request);
        GetRestaurantsReply reply = futureReply.get();
		assertNotNull(reply); 
		List<Restaurant> restaurants = reply.getRestaurantsList().stream()
					.map( restaurant -> { 
							Restaurant r = new Restaurant(restaurant.getName(), restaurant.getLocation()); 
							r.setId(restaurant.getRestaurantId());
							return r;
						}
					)
					.collect(Collectors.toList()); 
		assertEquals(0, restaurants.size()); 

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

}