package asw.efood.restaurantservice.api.event;

import asw.efood.common.api.event.DomainEvent; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class RestaurantCreatedEvent implements DomainEvent {

	private Long id; 
	private String name; 
	private String location; 
	
}
