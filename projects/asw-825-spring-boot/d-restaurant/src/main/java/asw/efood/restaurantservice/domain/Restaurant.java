package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

@Entity 
@Data @NoArgsConstructor
public class Restaurant {

	@Id 
	@GeneratedValue
	private Long id; 
	private String name; 
	private String location; 
	
	public Restaurant(String name, String location) {
		this(); 
		this.name = name; 
		this.location = location; 
	}
	
}
