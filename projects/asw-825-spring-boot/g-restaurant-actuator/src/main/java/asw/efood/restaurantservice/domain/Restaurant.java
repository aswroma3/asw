package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*; 

@Entity 
@Data @NoArgsConstructor
public class Restaurant {

	@Id 
	@GeneratedValue
	private Long id; 
	private String name; 
	private String location; 
	
	@Embedded
	private RestaurantMenu menu;

	public Restaurant(String name, String location) {
		this(); 
		this.name = name; 
		this.location = location; 
		this.menu = new RestaurantMenu(new ArrayList<MenuItem>());
	}
	
	public Restaurant(String name, String location, RestaurantMenu menu) {
		this(); 
		this.name = name; 
		this.location = location; 
		this.menu = menu;
	}
	
}
