package asw.efood.restaurantservice.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import java.util.*; 
import java.util.stream.*;

@Service
public class RestaurantCommandHandler {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RestaurantService restaurantService;

	public void onCommand(Command command) {
		logger.info("PROCESSING COMMAND: " + command);
		if (command instanceof CreateRestaurantCommand cmd) {
			createRestaurant(cmd); 
		} else if (command instanceof CreateOrUpdateRestaurantMenuCommand cmd) {
			createOrUpdateRestaurantMenu(cmd); 
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

/*
	private void createOrUpdateRestaurantMenu(CreateOrUpdateRestaurantMenuCommand command) {
		// se solleva un'eccezione non viene catturata
//		restaurantService.createRestaurant(command.getName(), command.getLocation()); 
		// oppure, la cattura: 
		try {
			restaurantService.createOrUpdateRestaurantMenu(command.getName(), command.getLocation(), menuItemElementsToMenuItems(command.getMenuItems())); 
		} catch(RestaurantServiceException e) {
			logger.info("COMMAND: " + command + " THREW " + e.toString());	
		}
	}
*/ 

	private void createOrUpdateRestaurantMenu(CreateOrUpdateRestaurantMenuCommand command) throws RestaurantServiceException {
		// solleva un'eccezione se il ristorante con name e location non esiste ancora 
		// in questo caso, il messaggio viene riconsegnato e rielaborato fino a un massimo di altre 9 volte 
		restaurantService.createOrUpdateRestaurantMenu(command.getName(), command.getLocation(), menuItemElementsToMenuItems(command.getMenuItems())); 
	}

	private List<MenuItem> menuItemElementsToMenuItems(List<MenuItemElement> items) {
		return items 
				.stream()
				.map(i -> menuItemElementToMenuItem(i))
				.collect(Collectors.toList());
	}

	private MenuItem menuItemElementToMenuItem(MenuItemElement item) {
		return new MenuItem(item.getId(), item.getName(), item.getPrice());
	}
	
}

