package asw.ordermanager.productservice.api.rest;

import lombok.*; 

@Data
@NoArgsConstructor @AllArgsConstructor
public class GetProductResponse {

	private String name; 
	private int stockLevel; 
	private double price; 
	
}

