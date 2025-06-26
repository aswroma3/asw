package asw.efood.restaurantservice.api.command;

import asw.efood.common.api.command.Command; 

import lombok.*;

import java.util.*; 

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateOrUpdateRestaurantMenuCommand implements Command {

	private String name; 
	private String location; 
	private List<MenuItemElement> menuItems;
	
}
