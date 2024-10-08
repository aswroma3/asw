package asw.efood.restaurantservice.api.command;

import asw.efood.common.api.command.Command; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRestaurantCommand implements Command {

	private String name; 
	private String location; 
	
}
