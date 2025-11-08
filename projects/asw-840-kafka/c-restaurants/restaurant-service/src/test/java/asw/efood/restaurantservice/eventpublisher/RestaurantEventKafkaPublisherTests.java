package asw.efood.restaurantservice.eventpublisher;

import asw.efood.restaurantservice.domain.*;

import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.api.event.*; 
import asw.efood.restaurantservice.domain.RestaurantEventPublisher;

import asw.efood.restaurantservice.init.InitRestaurantDb; 

import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.annotation.DirtiesContext;  
import org.springframework.kafka.test.context.EmbeddedKafka; 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.util.*; 
import java.util.concurrent.TimeUnit; 
import java.util.concurrent.CountDownLatch;  

/* 
 * Test di integrazione per Kafka.
 * Devo verificare che il messaggio inviato dal RestaurantEventKafkaPublisher sia quello desiderato. 
 * Quindi mi serve un consumer di supporto per ricevere questo messaggio 
 */ 

@SpringBootTest(properties = {
		// modifico la configurazione del kafka consumer, 
		// per garantire la ricezione dei messaggi inviati durante il test 
		"spring.kafka.consumer.auto-offset-reset=earliest"
	})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class RestaurantEventKafkaPublisherTests {

    @Autowired
	RestaurantEventPublisher restaurantEventPublisher; 

	@MockitoBean 
	/* disabilita l'esecuzione di questo runner */ 
	private InitRestaurantDb runner; 

	private DomainEvent payload; 
    private CountDownLatch latch = new CountDownLatch(1);

    private void resetLatch() {
        latch = new CountDownLatch(1);
    }

    @KafkaListener(topics = RestaurantServiceEventChannel.channel, groupId="RestaurantEventKafkaPublisherTests")
    public void receive(ConsumerRecord<String, DomainEvent> record) {
        payload = record.value();
        latch.countDown();
    }

    @Test
	@DirtiesContext
    public void testPublishRestaurantCreatedEvent() throws Exception {
		// Arrange
		Long restaurantId = 101L; 
		Restaurant newRestaurant = new Restaurant("Seta", "Milano"); 
		newRestaurant.setId(restaurantId); 
		DomainEvent restaurantEvent = new RestaurantCreatedEvent(restaurantId, "Seta", "Milano"); 
		DomainEvent expectedEvent = new RestaurantCreatedEvent(restaurantId, "Seta", "Milano"); 

		// Mock 
		
		// Act
		resetLatch(); 
		restaurantEventPublisher.publish(restaurantEvent);

		// Assert
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertNotNull(payload);
        assertEquals(expectedEvent, payload);
		
		// Mock Verification 
    }

}
