package asw.efood.restaurantservice.commandlistener;

import asw.efood.restaurantservice.domain.*;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.CreateRestaurantCommand; 
import asw.efood.restaurantservice.api.command.RestaurantServiceCommandChannel;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.util.concurrent.TimeUnit; 
import java.util.concurrent.CountDownLatch; 

/* 
 * Test di integrazione per Kafka.
 * Devo verificare che il messaggio inviato da un publisher venga ricevuto dal command listener. 
 * Quindi mi serve un producer di supporto per inviare questo messaggio. 
 * Viene usato un latch per sincronizzare l'invio e la ricezione del messaggio durante il test. 
 * Infatti il test (che invia un messaggio) può fare le proprie asserzioni sulla ricezione del messaggio 
 * solo dopo che il messaggio è stato effettivamente ricevuto.  
 */ 

@SpringBootTest(properties = {
		// modifico la configurazione del kafka consumer, 
		// per garantire la ricezione dei messaggi inviati durante il test 
		"spring.kafka.consumer.auto-offset-reset=earliest"
	})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class RestaurantCommandKafkaListenerTests {

	private CountDownLatch latch;

    @MockitoBean
    private RestaurantCommandHandler restaurantCommandHandler;

	@Bean 
	/* estende il listener ai fini del test, per fargli decrementare il latch quando viene ricevuto un messaggio */ 
	public RestaurantCommandKafkaListener restaurantCommandListener() {
		return new RestaurantCommandKafkaListener() {
			@Override 
			public void listen(ConsumerRecord<String, Command> record) throws Exception {
				latch.countDown();
				super.listen(record); 
			}
		}; 
	}

    @Autowired
	private RestaurantCommandKafkaListener restaurantCommandListener;

    @Autowired
    private KafkaTemplate<String, Command> template;

	@BeforeEach
    public void resetLatch() {
        latch = new CountDownLatch(1);
    }	

    @Test
	@DirtiesContext
    public void testListenCreateRestaurantCommand() throws Exception {
		// Arrange
		Command command = new CreateRestaurantCommand("Seta", "Milano"); 
		Command expectedCommand = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 
		
		// Act
		template.send(RestaurantServiceCommandChannel.channel, command);

		// Assert
		boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
		
		// Mock Verification 
		verify(restaurantCommandHandler).onCommand(expectedCommand);
    }

    @Test
	@DirtiesContext
	/* Per verificare che i diversi test siano eseguiti indipendentemente */ 
    public void testListenAnotherCreateRestaurantCommand() throws Exception {
		// Arrange
		Command command = new CreateRestaurantCommand("Seta", "Roma"); 
		Command expectedCommand = new CreateRestaurantCommand("Seta", "Roma"); 

		// Mock 
		
		// Act
		template.send(RestaurantServiceCommandChannel.channel, command);

		// Assert
		boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
		
		// Mock Verification 
		verify(restaurantCommandHandler).onCommand(expectedCommand);
    }

    @Test
	@DirtiesContext
    public void testFailingListenCreateRestaurantCommand() throws Exception {
		// Arrange
		Command command = new CreateRestaurantCommand("Seta", "Milano"); 
		Command expectedCommand = new CreateRestaurantCommand("Seta", "Milano"); 

		// Mock 
		// se conCommand può sollevare un'eccezione 
//		doThrow(RestaurantServiceException.class).when(restaurantCommandHandler).onCommand(command);
		// oppure non serve comfigurare il mock  
		
		// Act
		template.send(RestaurantServiceCommandChannel.channel, command);

		// Assert
		boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
		
		/* 
		 * La richiesta di creazione del ristorante deve essere fatta al servizio una sola volta, 
		 * anche se il servizio solleva un'eccezione perché è violato il vincolo unique su name e location. 
		 * Questo test è importante per ché se l'operazione invocata dal KafkaListener solleva un'eccezione, 
		 * allora l'errore viene per default gestito ripetendo la consegna del messaggio, 
		 * fino ad un massimo di 10 volte. 
		 */ 
		// Mock Verification 
		verify(restaurantCommandHandler, times(1)).onCommand(expectedCommand);
    }

}
