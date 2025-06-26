package asw.efood.restaurantservice.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import org.springframework.dao.DataIntegrityViolationException;

/* 
 * Ancora test unitari, basati su JUnit e i Mock di Mockito
 * Questo è un tentativo di test socievole, in cui il repository e l'event publisher sono mockati, 
 * ma il command handler e il service sono quelli reali
 * 
 * Uso una Spy, che è un mock che incapsula un oggetto vero. 
 * Normalmente esegue le operazioni dell'oggetto vero, che però possono essere ridefinite per il test. 
 * Inoltre è possibile fare la verifica anche di una Spy. 
 * 
 * Mock is mainly used in unit tests where the Spring context is not needed or not loaded.
 * See https://medium.com/@ykods/difference-between-mock-and-mockbean-in-spring-testing-9576eb312cdb
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantCommandHandlerSociableTests {

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private RestaurantEventPublisher restaurantEventPublisher;

	@Spy 
	@InjectMocks 
	private RestaurantService restaurantService; 

	@InjectMocks 
	private RestaurantCommandHandler restaurantCommandHandler; 

    @BeforeEach
    void openMocks() {
		// garantisce che tutti gli oggetti usati nel test abbiano le loro dipendenze soddisfatte 
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void testOnCreateRestaurantCommand() {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 
		DomainEvent mockEvent = new RestaurantCreatedEvent(restaurantId, "Seta", "Milano"); 
		CreateRestaurantCommand command = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 
		when(restaurantRepository.save(any(Restaurant.class))).thenAnswer(
				r -> {
					Restaurant result = r.getArgument(0); 
					result.setId(restaurantId); 
					return result; 
				}
			);

		// Act
		restaurantCommandHandler.onCommand(command);

		// Assert
		
		// Mock Verification 
		verify(restaurantService).createRestaurant("Seta", "Milano");
		verify(restaurantRepository).save(newRestaurant);
		verify(restaurantEventPublisher).publish(mockEvent);
	}

	@Test
	public void testFailingOnCreateRestaurantCommand() {
		// Arrange
		CreateRestaurantCommand command = new CreateRestaurantCommand("Seta", "Milano"); 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 

		// Mock 
		when(restaurantRepository.save(newRestaurant)).thenThrow(DataIntegrityViolationException.class);

		// Act and Assert 
		// se onCommand può sollevare un'eccezione
//		assertThrows(RestaurantServiceException.class, () -> { restaurantCommandHandler.onCommand(command); }); 
		// altrimenti: 
		restaurantCommandHandler.onCommand(command);

		/* 
		 * la richiesta di creazione del ristorante deve essere fatta al servizio una sola volta, 
		 * anche se il servizio solleva un'eccezione perché è violato il vincolo unique su name e location 
		 */ 
		// Mock Verification 
		verify(restaurantService, times(1)).createRestaurant("Seta", "Milano");
		verify(restaurantRepository).save(newRestaurant);
		verify(restaurantEventPublisher, never()).publish(any(DomainEvent.class));
	}

}

