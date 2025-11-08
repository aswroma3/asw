package asw.efood.restaurantservice.commandlistener;

import asw.efood.common.api.command.Command; 
// import asw.efood.restaurantservice.api.command.*; 
import asw.efood.restaurantservice.api.command.RestaurantServiceCommandChannel;

import asw.efood.restaurantservice.domain.RestaurantCommandHandler;
import asw.efood.restaurantservice.domain.RestaurantServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean; 

import java.util.logging.Logger;

import org.springframework.kafka.listener.DefaultErrorHandler; 
import org.springframework.util.backoff.FixedBackOff; 

@Component 
public class RestaurantCommandKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RestaurantCommandHandler restaurantCommandHandler;

	/* 
	 * Se l'operazione invocata dal KafkaListener solleva un'eccezione, 
	 * allora l'errore viene per default gestito ripetendo la consegna del messaggio, 
	 * fino ad un massimo di 10 volte. 
	 * Tuttavia, l'error handler si può configurare per ignorare certe eccezioni, 
	 * ovvero per fare in modo che a fronte di quelle eccezioni 
	 * la consegna del messaggio non venga ripetuta. 
	 * Tuttavia, qui decidiamo che l'operazione (del command handler) 
	 * catturi l'eccezione anziché sollevarla, 
	 * quindi non serve modificare l'error handler. 
	 * Vedi anche: RestaurantCommandHandler 
	 */
/*	
	@Bean
	public DefaultErrorHandler errorHandler() {
		DefaultErrorHandler handler = new DefaultErrorHandler();
		handler.addNotRetryableExceptions(RestaurantServiceException.class);
		return handler;
	}
*/ 

	@Bean
	public DefaultErrorHandler errorHandler() {
		DefaultErrorHandler handler =  
			new DefaultErrorHandler((record, exception) -> {
				// recover after 10 failures, with 1sec back off 
				}, new FixedBackOff(1000L, 9L));
//		handler.addNotRetryableExceptions(RestaurantServiceException.class);
		return handler;
	}

	@KafkaListener(topics = RestaurantServiceCommandChannel.channel)
    public void listen(ConsumerRecord<String, Command> record) throws Exception {
        logger.info("COMMAND LISTENER: " + record.toString());
        Command command = record.value();
		restaurantCommandHandler.onCommand(command); 
    }

}
