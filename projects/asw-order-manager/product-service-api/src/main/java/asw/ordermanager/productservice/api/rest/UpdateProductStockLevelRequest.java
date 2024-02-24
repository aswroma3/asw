package asw.ordermanager.productservice.api.rest;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UpdateProductStockLevelRequest {

	private String name; 
	private int stockLevelVariation; 

}

