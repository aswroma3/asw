package asw.efood.restaurantservice.rest;

import asw.efood.restaurantservice.domain.*; 
import asw.efood.restaurantservice.api.rest.*; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger;

@RestController
@RequestMapping(path="/rest")
public class RestaurantRestController {

	@Autowired 
	private RestaurantService restaurantService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());
	
	/* Crea un nuovo ristorante. */ 
	@PostMapping("/restaurants")
	@ResponseStatus(HttpStatus.CREATED)
	public CreateRestaurantResponse createRestaurant(@RequestBody CreateRestaurantRequest request) {
		String name = request.getName();
		String location = request.getLocation();
		logger.info("REST CALL: createRestaurant " + name + ", " + location); 
		try {
			Restaurant restaurant = restaurantService.createRestaurant(name, location);
			CreateRestaurantResponse response = new CreateRestaurantResponse(restaurant.getId()); 
			logger.info("REST CALL: createRestaurant " + name + ", " + location + " --> CREATED"); 
			return response; 
		} catch (RestaurantServiceException e) {
			logger.info("REST CALL: createRestaurant " + name + ", " + location + " --> NOT CREATED"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant not created"
			);
		}
	}	

	/* Trova il ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}")
	public GetRestaurantResponse getRestaurant(@PathVariable Long restaurantId) {
		logger.info("REST CALL: getRestaurant " + restaurantId); 
		try {
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			GetRestaurantResponse response = restaurantToGetRestaurantResponse(restaurant); 
			logger.info("REST CALL: getRestaurant " + restaurantId + " --> FOUND"); 
			return response;
		} catch (RestaurantServiceException e) {
			logger.info("REST CALL: getRestaurant " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant not found"
			);
		}
	}

	private GetRestaurantResponse restaurantToGetRestaurantResponse(Restaurant restaurant) {
		if (restaurant!=null) {
			return new GetRestaurantResponse(restaurant.getId(), restaurant.getName(), restaurant.getLocation());
		} else {
			return null;
		}
	}
	
	/* Trova tutti i ristoranti. */ 
	@GetMapping("/restaurants")
	public GetRestaurantsResponse getAllRestaurants() {
		logger.info("REST CALL: getAllRestaurants"); 
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		Collection<GetRestaurantResponse> restaurantResponses = 
			restaurants
				.stream()
				.map(r -> restaurantToGetRestaurantResponse(r))
				.collect(Collectors.toList());
		return new GetRestaurantsResponse(restaurantResponses);
	}
	
	/* Trova il ristorante con name e location. */ 
	@GetMapping("/restaurants/findByNameAndLocation/")
	public GetRestaurantResponse getRestaurant(@RequestParam String name, @RequestParam String location) {
		logger.info("REST CALL: getRestaurant " + name + " " + location); 
		try {
			Restaurant restaurant = restaurantService.getRestaurant(name, location);
			GetRestaurantResponse response = restaurantToGetRestaurantResponse(restaurant); 
			logger.info("REST CALL: getRestaurant " + name + " " + location + " --> FOUND"); 
			return response;
		} catch (RestaurantServiceException e) {
			logger.info("REST CALL: getRestaurant " + name + " " + location + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant not found"
			);
		}
	}

	/* Trova tutti i ristoranti in location. */ 
	@GetMapping("/restaurants/findByLocation/")
	public GetRestaurantsResponse getRestaurantsByLocation(@RequestParam String location) {
		logger.info("REST CALL: getRestaurants " + location); 
		Collection<Restaurant> restaurants = restaurantService.getRestaurantsByLocation(location);
		Collection<GetRestaurantResponse> restaurantResponses = 
			restaurants
				.stream()
				.map(r -> restaurantToGetRestaurantResponse(r))
				.collect(Collectors.toList());
		return new GetRestaurantsResponse(restaurantResponses);
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

	/* Crea o modifica il menu del del ristorante con restaurantId. */ 
	@PutMapping("/restaurants/{restaurantId}/menu")
	public CreateRestaurantMenuResponse createOrUpdateRestaurantMenu(@RequestBody CreateRestaurantMenuRequest request) {
		Long restaurantId = request.getRestaurantId(); 
		List<MenuItem> menuItems = menuItemElementsToMenuItems(request.getMenuItems());
//			request.getMenuItems() 
//				.stream()
//				.map(i -> menuItemElementToMenuItem(i))
//				.collect(Collectors.toList());
		logger.info("REST CALL: createOrUpdateRestaurantMenu " + restaurantId + ", " + menuItems); 
		try {
			RestaurantMenu menu = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);
			CreateRestaurantMenuResponse response = new CreateRestaurantMenuResponse(menu.getId()); 
			return response; 
		} catch (RestaurantServiceException e) {
			logger.info("REST CALL: createOrUpdateRestaurantMenu " + restaurantId + ", " + menuItems + " --> NOT CREATED"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant menu not created/updated"
			);
		}
	}	

	/* Trova il menu del ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}/menu")
	public GetRestaurantMenuResponse getRestaurantMenu(@PathVariable Long restaurantId) {
		logger.info("REST CALL: getRestaurantMenu " + restaurantId); 
		try {
			RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
			List<MenuItemElement> menuItemElements = 
				menu.getMenuItems() 
					.stream()
					.map(i -> menuItemToMenuItemElement(i))
					.collect(Collectors.toList());
			GetRestaurantMenuResponse response = 
				new GetRestaurantMenuResponse(restaurantId, menuItemElements);
			return response; 
		} catch (RestaurantServiceException e) {
			logger.info("REST CALL: getRestaurantMenu " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant menu not found"
			);
		}
	}

	private MenuItemElement menuItemToMenuItemElement(MenuItem item) {
		return new MenuItemElement(item.getId(), item.getName(), item.getPrice());
	}

}
