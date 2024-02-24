package asw.efood.restaurantservice.rest;

import asw.efood.restaurantservice.domain.*; 
import asw.efood.restaurantservice.api.rest.*; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
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
	public CreateRestaurantResponse createRestaurant(@RequestBody CreateRestaurantRequest request) {
		String name = request.getName();
		String location = request.getLocation();
		logger.info("REST CALL: createRestaurant " + name + ", " + location); 
		Restaurant restaurant = restaurantService.createRestaurant(name, location);
		CreateRestaurantResponse response = new CreateRestaurantResponse(restaurant.getId()); 
		return response; 
	}	

//	/* Trova il ristorante con restaurantId. */ 
//	@GetMapping("/restaurants/{restaurantId}")
//	public GetRestaurantResponse getRestaurant(@PathVariable Long restaurantId) {
//		logger.info("REST CALL: getRestaurant " + restaurantId); 
//		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
//		GetRestaurantResponse response = restaurantToGetRestaurantResponse(restaurant); 
//		return response;
//	}

	/* Trova il ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}")
	public GetRestaurantResponse getRestaurantWith404(@PathVariable Long restaurantId) {
		logger.info("REST CALL: getRestaurant " + restaurantId); 
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		if (restaurant!=null) {
			GetRestaurantResponse response = restaurantToGetRestaurantResponse(restaurant); 
			return response;
		} else {
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
	
}
