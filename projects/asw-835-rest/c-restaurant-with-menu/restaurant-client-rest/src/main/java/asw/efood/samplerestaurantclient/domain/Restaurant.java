package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class Restaurant {

	private Long id; 
	private String name; 
	private String location; 
	
	private List<MenuItem> menuItems;

	public Restaurant(Long id, String name, String location) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.location = location; 
	}

	public String toString() {
		return "Restaurant(" + 
			"id=" + id + 
			", name=" + name + 
			", location=" + location + 
			(menuItems!=null ? ", menuItems=" + menuItems : "") + 
			")"; 
	}
	
}
