package asw.efood.restaurantservice.api.rest;

import java.util.*; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetRestaurantMenuResponse {

	private Long restaurantId;
	private Collection<MenuItemElement> menuItems;

}

