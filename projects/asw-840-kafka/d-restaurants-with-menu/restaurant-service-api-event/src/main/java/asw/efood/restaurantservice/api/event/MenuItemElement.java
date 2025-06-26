package asw.efood.restaurantservice.api.event;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class MenuItemElement {

	private String id;
	private String name; 
	private double price;	
}

