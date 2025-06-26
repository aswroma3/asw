package asw.efood.samplerestauranteventlistener.restauranteventlistener;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import asw.efood.samplerestauranteventlistener.domain.RestaurantEventConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RestaurantEventKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RestaurantEventConsumer restaurantEventConsumer;

	@KafkaListener(topics = RestaurantServiceEventChannel.channel)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
		restaurantEventConsumer.onEvent(event); 
    }

}
