package asw.efood.restaurantservice.web;

import asw.efood.restaurantservice.domain.*; 

import lombok.*; 

import java.util.*; 

@Data @NoArgsConstructor
public class EditRestaurantMenuForm {

	private List<MenuItem> menuItems;
	
	public EditRestaurantMenuForm(List<MenuItem> menuItems) {
		this.menuItems = menuItems; 
	}

}
