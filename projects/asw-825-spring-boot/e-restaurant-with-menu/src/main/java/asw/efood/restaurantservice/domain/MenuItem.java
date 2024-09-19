package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class MenuItem {

	private String id;
	private String name;
	private double price;
	
}
