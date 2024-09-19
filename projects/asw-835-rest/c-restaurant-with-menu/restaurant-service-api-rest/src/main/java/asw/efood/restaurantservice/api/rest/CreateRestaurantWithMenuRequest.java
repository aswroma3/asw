package asw.efood.restaurantservice.api.rest;

import java.util.*; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRestaurantWithMenuRequest {

	private String name; 
	private String location;	
	
	private Collection<MenuItemElement> menuItems;

}

