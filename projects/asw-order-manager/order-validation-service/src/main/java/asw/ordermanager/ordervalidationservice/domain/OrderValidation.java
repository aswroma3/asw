package asw.ordermanager.ordervalidationservice.domain;

import java.util.*; 

import lombok.*; 

/* Ordine con validazione. */ 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderValidation {

	@EqualsAndHashCode.Include
	private Long id; 
	private String customer; 
	private List<OrderItem> orderItems;
	private boolean isValid; 
	private String motivation; 

	public OrderValidation(Long id, Order order, boolean isValid, String motivation) {
		this.id = id;
		if (order!=null) {
			this.customer = order.getCustomer(); 
			this.orderItems = order.getOrderItems(); 
		}
		this.isValid = isValid; 
		this.motivation = motivation; 
	}
	
}
