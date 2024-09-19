package asw.efood.restaurantservice.api.rest;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRestaurantRequest {

	private String name; 
	private String location;	

}

