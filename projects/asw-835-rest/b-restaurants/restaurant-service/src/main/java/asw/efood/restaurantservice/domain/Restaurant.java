package asw.efood.restaurantservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

@Entity 
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNameLocation", columnNames = { "name", "location" }) })
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
