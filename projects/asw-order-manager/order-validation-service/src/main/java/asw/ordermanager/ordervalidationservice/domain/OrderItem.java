package asw.ordermanager.ordervalidationservice.domain;

import lombok.*; 

/* Ordine. */ 
@Data 
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	private String product; 
	private int quantity; 
	
}
