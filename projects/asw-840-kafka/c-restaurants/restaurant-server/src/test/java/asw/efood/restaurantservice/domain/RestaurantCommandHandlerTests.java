package asw.efood.restaurantservice.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.*; 

/* 
 * Ancora test unitari, basati su JUnit e i Mock di Mockito
 * 
 * Mock is mainly used in unit tests where the Spring context is not needed or not loaded.
 * See https://medium.com/@ykods/difference-between-mock-and-mockbean-in-spring-testing-9576eb312cdb
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantCommandHandlerTests {

	@Mock
	private RestaurantService restaurantService;

	@InjectMocks 
	private RestaurantCommandHandler restaurantCommandHandler; 

	@Test
	public void testOnCreateRestaurantCommand() {
		// Arrange
		CreateRestaurantCommand command = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 

		// Act
		restaurantCommandHandler.onCommand(command);

		// Assert
		
		/* 
		 * la richiesta di creazione del ristorante deve essere fatta al servizio una sola volta, 
		 * anche se il servizio solleva un'eccezione perché è violato il vincolo unique su name e location 
		 */ 
		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
	}

	@Test
	public void testFailingOnCreateRestaurantCommand() {
		// Arrange
		CreateRestaurantCommand command = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 
		when(restaurantService.createRestaurant("Seta", "Milano")).thenThrow(RestaurantServiceException.class);

		// Act and Assert
		// se onCommand può sollevare un'eccezione
//		assertThrows(RestaurantServiceException.class, () -> { restaurantCommandHandler.onCommand(command); }); 
		// altrimenti: 
		restaurantCommandHandler.onCommand(command);

		// Assert
		
		/* 
		 * la richiesta di creazione del ristorante deve essere fatta al servizio una sola volta, 
		 * anche se il servizio solleva un'eccezione perché è violato il vincolo unique su name e location 
		 */ 
		// Mock Verification 
		verify(restaurantService, times(1)).createRestaurant("Seta", "Milano");
	}

}

