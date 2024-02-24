package asw.ordermanager.orderservice.api.rest;

import java.util.*; 

import lombok.*; 

@Data 
@NoArgsConstructor @AllArgsConstructor
public class GetOrderResponse {

	private Long id; 
	private String customer; 
	private List<OrderItemElement> orderItems;	
	private double total; 

}

