package asw.efood.samplerestauranteventlistener.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*;

import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

/* 
 * Test unitari, basati su JUnit e Mockito 
 */ 

@ExtendWith(MockitoExtension.class)
public class RestaurantEventConsumerTests {
	
	@Spy 
	private RestaurantEventConsumer restaurantEventConsumer; 

	@Test
	public void testOnRestaurantCreatedTest() {
		// Arrange
		DomainEvent event = new RestaurantCreatedEvent(101L, "Seta", "Milano"); 
		RestaurantCreatedEvent expectedEvent = new RestaurantCreatedEvent(101L, "Seta", "Milano"); 

		// Mock 
		
		// Act
		restaurantEventConsumer.onEvent(event);

		// Assert
		
		// MockVerification 
		verify(restaurantEventConsumer).onRestaurantCreated(expectedEvent);
	}
	
}

