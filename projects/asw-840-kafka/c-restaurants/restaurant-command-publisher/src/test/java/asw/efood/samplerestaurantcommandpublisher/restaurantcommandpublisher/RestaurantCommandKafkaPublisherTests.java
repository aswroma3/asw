package asw.efood.samplerestaurantcommandpublisher.restaurantcommandpublisher;

import asw.efood.samplerestaurantcommandpublisher.domain.*;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.CreateRestaurantCommand; 
import asw.efood.restaurantservice.api.command.RestaurantServiceCommandChannel; 

import asw.efood.samplerestaurantcommandpublisher.domain.RestaurantCommandPublisherRunner; 

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
 * Devo verificare che il messaggio inviato dal RestaurantCommandKafkaPublisher sia quello desiderato. 
 * Quindi mi serve un consumer di supporto per ricevere questo messaggio 
 */ 

@SpringBootTest(properties = { 
		// configuro il kafka consumer, usato solo per il test 
		"spring.kafka.consumer.group-id=${spring.application.name}", 
		"spring.kafka.consumer.auto-offset-reset=earliest", 
		"spring.kafka.consumer.auto-offset-reset=earliest",
		"spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer", 
		"spring.kafka.consumer.properties.spring.json.trusted.packages=asw.efood.*"
	})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class RestaurantCommandKafkaPublisherTests {

    @Autowired
	RestaurantCommandPublisher restaurantCommandPublisher; 

	@MockitoBean 
	/* disabilita l'esecuzione di questo runner */ 
	private RestaurantCommandPublisherRunner runner; 

	private Command payload; 
    private CountDownLatch latch = new CountDownLatch(1);

    private void resetLatch() {
        latch = new CountDownLatch(1);
    }

    @KafkaListener(topics = RestaurantServiceCommandChannel.channel, groupId="RestaurantCommandKafkaPublisherTests")
    public void receive(ConsumerRecord<String, Command> record) {
        payload = record.value();
        latch.countDown();
    }

	@Test
	@DirtiesContext
	public void testPublishCreateRestaurantCommand() throws Exception {
		// Arrange
		Command restaurantCommand = new CreateRestaurantCommand("Seta", "Milano"); 
		Command expectedCommand = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 
		
		// Act
		resetLatch(); 
		restaurantCommandPublisher.publish(restaurantCommand);

		// Assert
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertNotNull(payload);
        assertEquals(expectedCommand, payload);
		
		// Mock Verification 
    }

}
