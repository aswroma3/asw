package asw.efood.restaurantservice.init;

import asw.efood.restaurantservice.domain.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Component
public class InitRestaurantDb implements CommandLineRunner {

//	@Autowired 
//	private RestaurantRepository restaurantRepository; 

	@Autowired 
	private RestaurantService restaurantService; 

	public void run(String[] args) {		
 		Restaurant restaurant; 
//		RestaurantMenu menu; 
		List<MenuItem> menuItems; 
		
		menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 15.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 14.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 14.0) );
		restaurantService.createRestaurantWithMenu( "Hostaria dell'Orso", "Roma", menuItems );	
//		menu = new RestaurantMenu(menuItems);
//		restaurant = new Restaurant( "Hostaria dell'Orso", "Roma", menu );		
//		restaurantRepository.save(restaurant); 
		
		menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("MAR", "Pizza margherita", 6.0) ); 
		menuItems.add( new MenuItem("CAP", "Pizza Capricciosa", 8.0) ); 
		menuItems.add( new MenuItem("FIO", "Pizza fiori di zucca e alici", 7.5) );
		restaurantService.createRestaurantWithMenu( "Baffetto", "Roma", menuItems );	
//		menu = new RestaurantMenu(menuItems);
//		restaurant = new Restaurant( "Baffetto", "Roma", menu );
//		restaurantRepository.save(restaurant); 
		
		menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("CAR", "Carbonara", 12.0) ); 
		menuItems.add( new MenuItem("GRI", "Gricia", 11.0) ); 
		menuItems.add( new MenuItem("AMA", "Amatriciana", 12.0) );
		restaurantService.createRestaurantWithMenu( "L'Omo", "Roma", menuItems );	
//		menu = new RestaurantMenu(menuItems);
//		restaurant = new Restaurant( "L'Omo", "Roma", menu );
//		restaurantRepository.save(restaurant); 
		
		menuItems = new ArrayList<>(); 
		menuItems.add( new MenuItem("RIP", "Risotto ai funghi porcini", 25.0) ); 
		menuItems.add( new MenuItem("TAG", "Tagliata di manzo", 35.0) ); 
		menuItems.add( new MenuItem("CRE", "Crema catalana", 12.0) );
		restaurantService.createRestaurantWithMenu( "Seta", "Milano", menuItems );	
//		menu = new RestaurantMenu(menuItems);
//		restaurant = new Restaurant( "Seta", "Milano", menu );
//		restaurantRepository.save(restaurant); 
	}
	
}
