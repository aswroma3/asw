package asw.ordermanager.orderservice.domain;

import jakarta.persistence.*; 

import lombok.*; 

/* Ordine. */ 
@Embeddable
@Data 
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	private String product; 
	private int quantity; 
	
}
