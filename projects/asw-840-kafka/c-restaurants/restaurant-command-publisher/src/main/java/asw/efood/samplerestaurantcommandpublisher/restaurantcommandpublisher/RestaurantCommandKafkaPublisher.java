package asw.efood.samplerestaurantcommandpublisher.restaurantcommandpublisher;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 
import asw.efood.samplerestaurantcommandpublisher.domain.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

@Service
public class RestaurantCommandKafkaPublisher implements RestaurantCommandPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, Command> template;

	private String channel = RestaurantServiceCommandChannel.channel; 

    @Override
    public void publish(Command command) {
        logger.info("COMMAND PUBLISHER: " + command.toString() + " ON CHANNEL: " + channel);
        template.send(channel, command);
        // template.flush();
    }

}
