package asw.ordermanager.productservice.api.rest;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CreateProductRequest {

	private String name; 
	private String category; 
	private int stockLevel; 
	private double price; 

}

