package asw.efood.restaurantservice.web;

import asw.efood.restaurantservice.domain.*; 

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*; 

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/web")
public class RestaurantWebController {

	@Autowired 
	private RestaurantService restaurantService; 
	
    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Trova il ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurant(Model model, @PathVariable Long restaurantId) {
		logger.info("WEB CALL: getRestaurant " + restaurantId); 
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}

	/* Trova tutti i ristoranti. */ 
	@GetMapping("/restaurants")
	public String getRestaurants(Model model) {
		logger.info("WEB CALL: getAllRestaurants"); 
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addAttribute("restaurants", restaurants);
		return "get-restaurants";
	}
	
	/* Crea un nuovo ristorante (form). */ 
	@GetMapping(value="/restaurants", params={"add"})
	public String getAddRestaurantForm(Model model) {
		model.addAttribute("form", new AddRestaurantForm());
		return "add-restaurant-form";
	}
	
	/* Crea un nuovo ristorante. */ 
	@PostMapping("/restaurants")
	public String addRestaurant(Model model, @ModelAttribute("form") AddRestaurantForm form) {
		String name = form.getName();
		String location = form.getLocation();
		logger.info("WEB CALL: createRestaurant " + name + ", " + location); 
		Restaurant restaurant = restaurantService.createRestaurant(name, location);
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}	

}
