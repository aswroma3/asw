package asw.efood.samplerestaurantclient.restaurantclient.grpc;

import asw.efood.samplerestaurantclient.domain.*;
import asw.efood.restaurantservice.api.grpc.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.context.annotation.Bean; 
import org.springframework.beans.factory.annotation.Value; 

import io.grpc.stub.StreamObserver;

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

import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import java.util.*; 
import java.util.stream.*; 

import io.grpc.*; 

import java.util.logging.Logger;
import java.io.IOException;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;

import java.util.concurrent.TimeUnit; 
import java.util.concurrent.ExecutionException;

import io.grpc.inprocess.InProcessServerBuilder; 

/* 
 * Test di integrazione per il client gRPC. 
 * Viene testata l'API gRPC.  
 */ 

@SpringBootTest(properties = {
		"asw.efood.restaurantservice.grpc.inprocessserver=true",
		"asw.efood.restaurantservice.grpc.inprocessservername=TestClient"
	}) 
@DirtiesContext 
@ExtendWith(MockitoExtension.class)
public class RestaurantClientGrpcAdapterTests {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	private interface RestaurantService {
		public Restaurant createRestaurant(String name, String location); 
		public Restaurant getRestaurant(Long id); 
		public Restaurant getRestaurant(String name, String location); 
		public Collection<Restaurant> getAllRestaurants(); 
		public Collection<Restaurant> getRestaurantsByLocation(String location); 
	}

    @MockitoBean 
	private RestaurantService restaurantService;

	/* gRPC server usato per il test */ 
    private class RestaurantServiceImpl extends RestaurantServiceGrpc.RestaurantServiceImplBase {

        @Override
        public void createRestaurant(CreateRestaurantRequest request, StreamObserver<CreateRestaurantReply> responseObserver) {
            String name = request.getName();
            String location = request.getLocation();
			try {
				Restaurant restaurant = restaurantService.createRestaurant(name, location);
				CreateRestaurantReply reply = CreateRestaurantReply.newBuilder()
						.setRestaurantId(restaurant.getId())
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				responseObserver.onError(io.grpc.Status.ALREADY_EXISTS.withDescription("Restaurant not created").asRuntimeException());
			}				
		}

        @Override
        public void getRestaurantById(GetRestaurantByIdRequest request, StreamObserver<GetRestaurantReply> responseObserver) {
            Long restaurantId = request.getRestaurantId(); 
			try {
				Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
				GetRestaurantReply reply = GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Restaurant not found").asRuntimeException());
			}
        }

        @Override
        public void getRestaurantByNameAndLocation(GetRestaurantByNameAndLocationRequest request, StreamObserver<GetRestaurantReply> responseObserver) {
            String name = request.getName(); 
            String location = request.getLocation(); 
			try {
				Restaurant restaurant = restaurantService.getRestaurant(name, location);
				GetRestaurantReply reply = GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			} catch (RestaurantServiceException e) {
				responseObserver.onError(io.grpc.Status.NOT_FOUND.withDescription("Restaurant not found").asRuntimeException());
				responseObserver.onCompleted();
			}
		}

        @Override
        public void getAllRestaurants(GetAllRestaurantsRequest request, StreamObserver<GetRestaurantsReply> responseObserver) {
			Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
			List<GetRestaurantReply> rr = 
				restaurants.stream() 
					.map(restaurant -> GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build())
					.collect(Collectors.toList()); 
            GetRestaurantsReply reply = GetRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
		}

        @Override
        public void getRestaurantsByLocation(GetRestaurantsByLocationRequest request, StreamObserver<GetRestaurantsReply> responseObserver) {
            String location = request.getLocation(); 
			Collection<Restaurant> restaurants = restaurantService.getRestaurantsByLocation(location);
			List<GetRestaurantReply> rr = 
				restaurants.stream() 
					.map(restaurant -> GetRestaurantReply.newBuilder()
                        .setRestaurantId(restaurant.getId())
                        .setName(restaurant.getName())
                        .setLocation(restaurant.getLocation())
                        .build())
					.collect(Collectors.toList()); 
            GetRestaurantsReply reply = GetRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

	}

	@MockitoBean 
	/* disabilita l'esecuzione di questo runner */ 
	private RestaurantClientRunner runner; 

	@Autowired 
	private RestaurantClientPort restaurantClientPort;

    private Server server;

	@Value("${asw.efood.restaurantservice.grpc.inprocessservername:}")
    private String serverName;

	@BeforeEach
	//@PostConstruct
	void startServer() throws IOException {
		server = InProcessServerBuilder
				.forName(serverName)
				.addService(new RestaurantServiceImpl())
				.directExecutor()
				.build()
				.start(); 
		logger.info("Inprocess server " + serverName + " started");
	}

	@AfterEach
	//@PreDestroy
	void stopServer() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			logger.info("*** gRPC server shut down");
		}
	}

	@Test
	@DirtiesContext 
	public void testCreateRestaurant() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant(restaurantId, "Seta", "Milano"); 

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenReturn(newRestaurant);
		
		// Act 
		Long result = restaurantClientPort.createRestaurant("Seta", "Milano"); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result);

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
		Exception e = assertThrows(RestaurantServiceException.class, () -> { 
				restaurantClientPort.createRestaurant(restaurantName, restaurantLocation);
			}); 
//		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: ALREADY_EXISTS"));

		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantById() throws Exception {
		// Arrange
		Long restaurantId = 42L; 
		Restaurant mockRestaurant = new Restaurant(restaurantId, "Seta", "Milano"); 
		Restaurant expectedRestaurant = new Restaurant(restaurantId, "Seta", "Milano"); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantId)).thenReturn(mockRestaurant);

		// Act 
		Restaurant result = restaurantClientPort.getRestaurant(restaurantId); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		// oppure così (verifica gli oggetti campo a campo) 
		assertEquals(expectedRestaurant, result);

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
		Exception e = assertThrows(RestaurantServiceException.class, () -> { 
				restaurantClientPort.getRestaurant(restaurantId);
			}); 
//		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: NOT_FOUND"));
				
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
		Restaurant mockRestaurant = new Restaurant(restaurantId, restaurantName, restaurantLocation); 
		Restaurant expectedRestaurant = new Restaurant(restaurantId, restaurantName, restaurantLocation); 

		// Mock 
		when(restaurantService.getRestaurant(restaurantName, restaurantLocation)).thenReturn(mockRestaurant);

		// Act 
		Restaurant result = restaurantClientPort.getRestaurantByNameAndLocation(restaurantName, restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(restaurantId, result.getId());
		assertEquals("Seta", result.getName());
		assertEquals("Milano", result.getLocation());
		// oppure così (verifica gli oggetti campo a campo) 
		assertEquals(expectedRestaurant, result);

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
		Exception e = assertThrows(RestaurantServiceException.class, () -> { 
				restaurantClientPort.getRestaurantByNameAndLocation(restaurantName, restaurantLocation);
			}); 
//		assertTrue(e.getMessage().contains("io.grpc.StatusRuntimeException: NOT_FOUND"));

		// Mock Verification 
		verify(restaurantService).getRestaurant(restaurantName, restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testGetAllRestaurants() throws Exception {
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "Seta", "Milano"); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(2L, "L'Omo", "Roma"); 
		mockRestaurants.add(mr2); 
		Restaurant mr3 = new Restaurant(3L, "L'Omo", "Roma"); 
		mockRestaurants.add(mr3); 

		// Mock  
		when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getAllRestaurants(); 

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));
		assertTrue(result.contains(mr3));

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	@DirtiesContext 
	public void testGetAllRestaurantsWithNoRestaurants() throws Exception {
		List<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurants);

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getAllRestaurants(); 

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());

		// Mock Verification 
		verify(restaurantService).getAllRestaurants();
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantsByLocation() throws Exception {
		String restaurantLocation = "Roma"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 
		Restaurant mr1 = new Restaurant(1L, "L'Omo", restaurantLocation); 
		mockRestaurants.add(mr1); 
		Restaurant mr2 = new Restaurant(7L, "Baffetto", restaurantLocation); 
		mockRestaurants.add(mr2); 

		// Mock  
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getRestaurantsByLocation(restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(mr1));
		assertTrue(result.contains(mr2));

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

	@Test
	@DirtiesContext 
	public void testGetRestaurantsByLocationWithNoRestaurants() throws Exception {
		String restaurantLocation = "Mialno"; 
		List<Restaurant> mockRestaurants = new ArrayList<>(); 

		// Mock  
		when(restaurantService.getRestaurantsByLocation(restaurantLocation)).thenReturn(mockRestaurants);

		// Act 
		Collection<Restaurant> result = restaurantClientPort.getRestaurantsByLocation(restaurantLocation); 

		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());

		// Mock Verification 
		verify(restaurantService).getRestaurantsByLocation(restaurantLocation);
	}

}