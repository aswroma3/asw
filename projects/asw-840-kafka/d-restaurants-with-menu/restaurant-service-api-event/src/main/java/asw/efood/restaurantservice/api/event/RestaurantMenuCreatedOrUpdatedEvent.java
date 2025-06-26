package asw.efood.restaurantservice.api.event;

import asw.efood.common.api.event.DomainEvent; 

import lombok.*;

import java.util.*; 

@Data @NoArgsConstructor @AllArgsConstructor
public class RestaurantMenuCreatedOrUpdatedEvent implements DomainEvent {

	private Long id; 
	private List<MenuItemElement> menuItems;
	
}
