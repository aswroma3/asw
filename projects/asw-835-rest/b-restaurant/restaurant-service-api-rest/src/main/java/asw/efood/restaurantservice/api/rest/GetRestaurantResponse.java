package asw.efood.restaurantservice.api.rest;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetRestaurantResponse {

	private Long restaurantId;
	
	private String name; 
	private String location;	
}

