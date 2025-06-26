package asw.efood.samplerestauranteventlistener.domain;

import lombok.*; 

import java.util.*;

@Data 
@NoArgsConstructor @AllArgsConstructor
public class RestaurantMenu {

	private Long restaurantId; 
	private List<MenuItem> menuItems;
	
}
