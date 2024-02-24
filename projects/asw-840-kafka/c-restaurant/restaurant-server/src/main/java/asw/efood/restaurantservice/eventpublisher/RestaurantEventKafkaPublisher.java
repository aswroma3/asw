package asw.efood.restaurantservice.eventpublisher;

import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.api.event.RestaurantServiceEventChannel; 
import asw.efood.restaurantservice.domain.RestaurantEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RestaurantEventKafkaPublisher implements RestaurantEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

	private String channel = RestaurantServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }

}
