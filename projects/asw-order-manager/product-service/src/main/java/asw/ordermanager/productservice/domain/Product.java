package asw.ordermanager.productservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

/* Prodotto con inventario. */ 
@Entity 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Comparable<Product> {

	@Id 
	@EqualsAndHashCode.Include
	private String name; 
	private String category; 
	/* quantità disponibile */ 
	private int stockLevel; 
	/* prezzo di listino */ 
	private double price; 

	public Product(String name, String category, int stockLevel, double price) {
		this(); 
		this.name = name; 
		this.category = category; 
		this.stockLevel = stockLevel; 
		this.price = price; 
	}

	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.name); 
	}
	
}
