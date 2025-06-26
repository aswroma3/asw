package asw.efood.restaurantservice.api.rest;

import lombok.*;

import java.util.*; 

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRestaurantMenuRequest {

	private Long restaurantId;	
	private List<MenuItemElement> menuItems;

}

