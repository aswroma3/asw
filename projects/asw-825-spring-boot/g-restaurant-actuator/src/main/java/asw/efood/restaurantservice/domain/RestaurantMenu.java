package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*;

@Embeddable
@Data @NoArgsConstructor
public class RestaurantMenu {

	@ElementCollection
	private List<MenuItem> menuItems;

	public RestaurantMenu(List<MenuItem> menuItems) {
		this(); 
		this.menuItems = menuItems; 
	}
	
}
