package asw.efood.restaurantservice.commandlistener;

import asw.efood.common.api.command.Command; 
// import asw.efood.restaurantservice.api.command.*; 
import asw.efood.restaurantservice.api.command.RestaurantServiceCommandChannel;

import asw.efood.restaurantservice.domain.RestaurantCommandHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RestaurantCommandKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RestaurantCommandHandler restaurantCommandHandler;

	@KafkaListener(topics = RestaurantServiceCommandChannel.channel)
    public void listen(ConsumerRecord<String, Command> record) throws Exception {
        logger.info("COMMAND LISTENER: " + record.toString());
        Command command = record.value();
		restaurantCommandHandler.onCommand(command); 
    }



}
