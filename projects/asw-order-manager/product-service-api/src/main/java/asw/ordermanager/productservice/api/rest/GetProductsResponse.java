package asw.ordermanager.productservice.api.rest;

import java.util.*; 

import lombok.*; 

@Data
@NoArgsConstructor @AllArgsConstructor
public class GetProductsResponse {

	private List<GetProductResponse> products; 
	
}

