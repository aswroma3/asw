package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor
public class RestaurantMenu {

	@Id 
	@Column(name = "restaurant_id")
	private Long id; 

	@ElementCollection(fetch = FetchType.EAGER)
	private List<MenuItem> menuItems;
	
	public RestaurantMenu(Long id) {
		this.id = id; 
		this.menuItems = new ArrayList<>(); 
	}

}
