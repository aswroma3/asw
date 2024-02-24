package asw.efood.restaurantservice.init;

import asw.efood.restaurantservice.domain.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class InitRestaurantDb implements CommandLineRunner {

	@Autowired 
	private RestaurantService restaurantService; 

	public void run(String[] args) {		
		restaurantService.createRestaurant( "Hostaria dell'Orso", "Roma" );	
		restaurantService.createRestaurant( "Baffetto", "Roma" );	
		restaurantService.createRestaurant( "L'Omo", "Roma" );	
		restaurantService.createRestaurant( "Seta", "Milano" );	
	}
	
}
