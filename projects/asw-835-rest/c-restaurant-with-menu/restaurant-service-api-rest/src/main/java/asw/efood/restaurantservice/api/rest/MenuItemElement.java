package asw.efood.restaurantservice.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemElement {

	private String id;
	private String name; 
	private double price;	
}

