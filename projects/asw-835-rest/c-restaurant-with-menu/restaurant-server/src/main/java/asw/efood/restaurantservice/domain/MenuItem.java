package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

@Embeddable
@Data @NoArgsConstructor
public class MenuItem {

	private String id;
	private String name;
	private double price;

	public MenuItem(String id, String name, double price) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.price = price; 
	}
	
}
