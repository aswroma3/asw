package asw.efood.restaurantservice.api.rest;

import lombok.*;

import java.util.*; 

@Data @NoArgsConstructor @AllArgsConstructor
public class GetRestaurantsResponse {

	private Collection<GetRestaurantResponse> restaurants;
	
}

