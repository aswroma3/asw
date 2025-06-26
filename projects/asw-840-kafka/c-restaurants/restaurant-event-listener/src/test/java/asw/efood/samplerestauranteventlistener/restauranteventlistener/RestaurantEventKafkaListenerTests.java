package asw.efood.samplerestauranteventlistener.restauranteventlistener;

import asw.efood.samplerestauranteventlistener.domain.*;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.annotation.DirtiesContext;  
import org.springframework.kafka.test.context.EmbeddedKafka; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean; 

import org.springframework.kafka.core.KafkaTemplate;

//import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.concurrent.TimeUnit; 
import java.util.concurrent.CountDownLatch; 

/* 
 * Test di integrazione per Kafka.
 * Devo verificare che il messaggio inviato da un publisher venga ricevuto dall'event listener. 
 * Quindi mi serve un producer di supporto per inviare questo messaggio. 
 * Viene usato un latch per sincronizzare l'invio e la ricezione del messaggio durante il test. 
 * Infatti il test (che invia un messaggio) può fare le proprie asserzioni sulla ricezione del messaggio 
 * solo dopo che il messaggio è stato effettivamente ricevuto.  
 */ 

@SpringBootTest(properties = {
		// modifico la configurazione del kafka consumer, 
		// per garantire la ricezione dei messaggi inviati durante il test 
		"spring.kafka.consumer.auto-offset-reset=earliest", 
		// configuro il kafka producer, usato solo per il test 
		"spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer", 
		"spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer"
	})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class RestaurantEventKafkaListenerTests {

	private CountDownLatch latch;

    @MockitoBean
    private RestaurantEventConsumer restaurantEventConsumer;

	@Bean 
	/* estende il listener ai fini del test, per fargli decrementare il latch quando viene ricevuto un messaggio */ 
	public RestaurantEventKafkaListener restaurantEventListener() {
		return new RestaurantEventKafkaListener() {
			@Override 
			public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
				latch.countDown();
				super.listen(record); 
			}
		}; 
	}

    @Autowired
	private RestaurantEventKafkaListener restaurantEventListener;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

	@BeforeEach
    public void resetLatch() {
        latch = new CountDownLatch(1);
    }	

    @Test
	@DirtiesContext
    public void testListenRestaurantCreatedEvent() throws Exception {
		// Arrange
		DomainEvent event = new RestaurantCreatedEvent(101L, "Seta", "Milano"); 
		DomainEvent expectedEvent = new RestaurantCreatedEvent(101L, "Seta", "Milano"); 

		// Mock 
		
		// Act
		template.send(RestaurantServiceEventChannel.channel, event);

		boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
		
		// Mock Verification 
		verify(restaurantEventConsumer).onEvent(expectedEvent);
    }

    @Test
	@DirtiesContext
	/* Per verificare che i diversi test siano eseguiti indipendentemente */ 
    public void testListenAnotherRestaurantCreatedEvent() throws Exception {
		// Arrange
		DomainEvent event = new RestaurantCreatedEvent(102L, "Seta", "Roma"); 
		DomainEvent expectedEvent = new RestaurantCreatedEvent(102L, "Seta", "Roma"); 

		// Mock 
		
		// Act
		template.send(RestaurantServiceEventChannel.channel, event);

		// Assert
		boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
		
		// Mock Verification 
		verify(restaurantEventConsumer).onEvent(expectedEvent);
    }

}
