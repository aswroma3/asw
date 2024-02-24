package asw.ordermanager.orderservice.api.rest;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItemElement {

	private String product; 
	private int quantity; 

}

