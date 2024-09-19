package asw.efood.restaurantservice.api.rest;

import java.util.*; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetRestaurantsResponse {

	private Collection<GetRestaurantResponse> restaurants;
	
}

