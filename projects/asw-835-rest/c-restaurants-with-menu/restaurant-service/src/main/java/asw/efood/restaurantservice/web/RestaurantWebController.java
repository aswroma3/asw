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
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*; 

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/web")
public class RestaurantWebController {

	@Autowired 
	private RestaurantService restaurantService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());
	
	/* Crea un nuovo ristorante (form). */ 
	@GetMapping(value="/restaurants", params={"add"})
	public String getAddRestaurantForm(Model model) {
		model.addAttribute("form", new AddRestaurantForm());
		return "add-restaurant-form";
	}
	
	/* Crea un nuovo ristorante. */ 
	@PostMapping("/restaurants")
	@ResponseStatus(HttpStatus.CREATED)
	public String addRestaurant(Model model, @ModelAttribute("form") AddRestaurantForm form) {
		String name = form.getName();
		String location = form.getLocation();
		logger.info("WEB CALL: createRestaurant " + name + ", " + location); 
		try {
			Restaurant restaurant = restaurantService.createRestaurant(name, location);
			model.addAttribute("restaurant", restaurant);
			return "get-restaurant";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: createRestaurant " + name + ", " + location + " --> NOT CREATED"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant not created"
			);
		}
	}	

	/* Trova il ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurant(Model model, @PathVariable Long restaurantId) {
		logger.info("WEB CALL: getRestaurant " + restaurantId); 
		try {
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			model.addAttribute("restaurant", restaurant);
			logger.info("WEB CALL: getRestaurant " + restaurantId + " --> FOUND"); 
			return "get-restaurant";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: getRestaurant " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant not found"
			);
		}
	}

	/* Trova il menu del ristorante con restaurantId. */ 
	@GetMapping("/restaurants/{restaurantId}/menu")
	public String getRestaurantMenu(Model model, @PathVariable Long restaurantId) {
		logger.info("WEB CALL: getRestaurantMenu " + restaurantId); 
		try {
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("menu", menu);
			logger.info("WEB CALL: getRestaurantMenu " + restaurantId + " --> FOUND"); 
			return "get-restaurant-menu";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: getRestaurantMenu " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant or menu not found"
			);
		}
	}
	
	/* Trova tutti i ristoranti. */ 
	@GetMapping("/restaurants")
	public String getRestaurants(Model model) {
		logger.info("WEB CALL: getAllRestaurants"); 
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addAttribute("restaurants", restaurants);
		return "get-restaurants";
	}
	
	/* Trova il ristorante con name e location. */ 
	@GetMapping("/restaurants/findByNameAndLocation/")
	public String getRestaurantByNameAndLocation(Model model, @RequestParam String name, @RequestParam String location) {
		logger.info("WEB CALL: getRestaurant " + name + " " + location); 
		try {
			Restaurant restaurant = restaurantService.getRestaurant(name, location);
			model.addAttribute("restaurant", restaurant);
			logger.info("WEB CALL: getRestaurant " + name + " " + location + " --> FOUND"); 
			return "get-restaurant";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: getRestaurant " + name + " " + location + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant not found"
			);
		}
	}

	/* Trova tutti i ristoranti in location (form). */ 
	@GetMapping(value="/restaurants", params={"search"})
	public String getRestaurantsByLocationForm(Model model) {
		model.addAttribute("form", new GetRestaurantsByLocationForm());
		return "get-restaurants-by-location-form";
	}

	/* Trova tutti i ristoranti in location. */ 
	@GetMapping("/restaurants/findByLocation/")
	public String getRestaurantsByLocation(Model model, @RequestParam String location) {
		logger.info("WEB CALL: getRestaurants " + location); 
		Collection<Restaurant> restaurants = restaurantService.getRestaurantsByLocation(location);
		model.addAttribute("location", location).addAttribute("restaurants", restaurants);
		return "get-restaurants-by-location";
	}

	/* Crea o modifica il menu di un ristorante (form). */ 
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"edit"})
	public String getEditRestaurantMenuForm(Model model, @PathVariable Long restaurantId) {
		try {
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
			List<MenuItem> menuItems = menu.getMenuItems(); 
			if (menuItems==null) {
				menuItems = new ArrayList<>();
				// menu.setMenuItems(menuItems); 
			}		
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("form", new EditRestaurantMenuForm(menuItems));
			return "edit-restaurant-menu-form";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: getEditRestaurantMenuForm " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant or menu not found"
			);
		}
	}

	/* Crea o modifica il menu di un ristorante. */ 
	@PostMapping("/restaurants/{restaurantId}/menu")
	public String createOrUpdateRestaurantMenu(Model model, @PathVariable Long restaurantId, @ModelAttribute("form") EditRestaurantMenuForm form) {
		try {
			logger.info("WEB CALL: createOrUpdateRestaurantMenu " + restaurantId); 
			List<MenuItem> menuItems = form.getMenuItems(); 
			if (menuItems==null) {
				menuItems = new ArrayList<>();
			}
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			RestaurantMenu menu = restaurantService.createOrUpdateRestaurantMenu(restaurantId, menuItems);
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("menu", menu);
			return "get-restaurant";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: createOrUpdateRestaurantMenu " + restaurantId + " --> THREW EXCEPTION"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Restaurant menu not created/updated"
			);
		}
	}	

	/* Crea o modifica il menu di un ristorante (form). */ 
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"addMenuItem"})
	public String addMenuItem(Model model, @PathVariable Long restaurantId, @ModelAttribute("form") EditRestaurantMenuForm form) {
		try {
			List<MenuItem> menuItems = form.getMenuItems(); 
			if (menuItems==null) {
				menuItems = new ArrayList<>();
				form.setMenuItems(menuItems);
			}
			menuItems.add(new MenuItem());
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("form", form);
			return "edit-restaurant-menu-form";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: addMenuItem " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant or menu not found"
			);
		}
	}
	
	/* Crea o modifica il menu di un ristorante (form). */ 
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"removeMenuItem"})
	public String removeMenuItem(Model model, @PathVariable Long restaurantId, 
				@ModelAttribute("form") EditRestaurantMenuForm form, HttpServletRequest req) {
		try {
			int index = Integer.valueOf(req.getParameter("removeMenuItem")).intValue();
			form.getMenuItems().remove(index);
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("form", form);
			return "edit-restaurant-menu-form";
		} catch (RestaurantServiceException e) {
			logger.info("WEB CALL: removeMenuItem " + restaurantId + " --> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Restaurant or menu not found"
			);
		}
	}


}
