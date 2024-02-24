package asw.efood.restaurantservice.rest;

import asw.efood.restaurantservice.domain.*; 
import asw.efood.restaurantservice.api.rest.*; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 

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
	public CreateRestaurantResponse createRestaurant(@RequestBody CreateRestaurantRequest request) {
		String name = request.getName();
		String location = request.getLocation();
		logger.info("REST CALL: createRestaurant " + name + ", " + location); 
		Restaurant restaurant = restaurantService.createRestaurant(name, location);
		CreateRestaurantResponse response = new CreateRestaurantResponse(restaurant.getId()); 
		return response; 
	}	

	/* Crea o modifica il menu del del ristorante con restaurantId. */ 
	@PutMapping("/restaurants/{restaurantId}/menu")
	public CreateRestaurantMenuResponse createRestaurantMenu(@RequestBody CreateRestaurantMenuRequest request) {
		Long restaurantId = request.getRestaurantId(); 
		List<MenuItem> menuItems = 
			request.getMenuItems() 
				.stream()
				.map(i -> menuItemElementToMenuItem(i))
				.collect(Collectors.toList());
		logger.info("REST CALL: createRestaurantMenu " + restaurantId + ", " + menuItems); 
		Restaurant restaurant = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);
		CreateRestaurantMenuResponse response = new CreateRestaurantMenuResponse(restaurant.getId()); 
		return response; 
	}	

	private MenuItem menuItemElementToMenuItem(MenuItemElement item) {
		return new MenuItem(item.getId(), item.getName(), item.getPrice());
	}

	/* Trova il ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}")
	public GetRestaurantResponse getRestaurant(@PathVariable Long restaurantId) {
		logger.info("REST CALL: getRestaurant " + restaurantId); 
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		GetRestaurantResponse response = restaurantToGetRestaurantResponse(restaurant); 
		return response;
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
	public GetRestaurantsResponse getRestaurants() {
		logger.info("REST CALL: getAllRestaurants"); 
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		Collection<GetRestaurantResponse> restaurantResponses = 
			restaurants
				.stream()
				.map(r -> restaurantToGetRestaurantResponse(r))
				.collect(Collectors.toList());
		return new GetRestaurantsResponse(restaurantResponses);
	}
	
	/* Trova il menu del ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}/menu")
	public GetRestaurantMenuResponse getRestaurantMenu(@PathVariable Long restaurantId) {
		logger.info("REST CALL: getRestaurantMenu " + restaurantId); 
		RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
		List<MenuItemElement> menuItemElements = 
			menu.getMenuItems() 
				.stream()
				.map(i -> menuItemToMenuItemElement(i))
				.collect(Collectors.toList());
		GetRestaurantMenuResponse response = 
			new GetRestaurantMenuResponse(restaurantId, menuItemElements);
		return response; 
	}

	private MenuItemElement menuItemToMenuItemElement(MenuItem item) {
		return new MenuItemElement(item.getId(), item.getName(), item.getPrice());
	}

}
