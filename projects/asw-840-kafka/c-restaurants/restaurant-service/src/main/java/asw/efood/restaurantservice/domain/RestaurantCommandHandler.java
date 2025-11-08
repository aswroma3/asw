package asw.efood.restaurantservice.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@Service
public class RestaurantCommandHandler {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantService restaurantService;

	public void onCommand(Command command) {
		logger.info("PROCESSING COMMAND: " + command);
		if (command instanceof CreateRestaurantCommand cmd) {
			createRestaurant(cmd); 
		} else {
			logger.info("UNKNOWN COMMAND: " + command);			
		}
	}

	/* 
	 * L'operazione restaurantService.createRestaurant 
	 * può sollevare un'eccezione di tipo RestaurantServiceException. 
	 * Se anche questa operazione solleva un'eccezione anziché catturarla, allora, 
	 * per default, il messggio che ha causato l'eccezione viene assegnato da Kafka a un nuovo consumatore. 
	 * Per evitare che ciò avvenga, ci sono almeno due modi: 
	 * - catturare l'eccezione (quindi non viene più sollevata da questa operazione) 
	 * - configurare il KafkaListener per ignorare eccezioni di tipo RestaurantServiceException
	 * Vedi anche: RestaurantCommandKafkaListener. 
	 */ 
	
	private void createRestaurant(CreateRestaurantCommand command) {
		// se solleva un'eccezione non viene catturata
//		restaurantService.createRestaurant(command.getName(), command.getLocation()); 
		// oppure, la cattura: 
		try {
			restaurantService.createRestaurant(command.getName(), command.getLocation()); 
		} catch(RestaurantServiceException e) {
			logger.info("COMMAND: " + command + " THREW " + e.toString());	
		}
	}

}

