package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 
import java.util.function.Function; 

@Service 
public class OrderValidationServiceRestBasedImpl implements OrderValidationService {

	@Autowired 
	private OrderServiceClientPort orderServiceClient;

	@Autowired 
	private ProductServiceClientPort productServiceClient;

	/* Verifica la validità di un ordine. 
	 * Nota: con una sola chiamata REST trova tutti i prodotti dell'ordine. */ 
	public OrderValidation validateOrder(Long id) {
		Order order = orderServiceClient.getOrder(id); 
		String motivation = ""; 
		if (order==null) {
			motivation += "L'ordine " + id + " non esiste.";
			return new OrderValidation(id, order, false, motivation);
		}
		List<String> productNames = toProductNames(order.getOrderItems()); 
		List<Product> products = productServiceClient.getProductsByNames(productNames); 
		Map<String,Product> productMap = toProductMap(products); 
		
		boolean isValid = true; 
		for (OrderItem orderItem: order.getOrderItems()) {
			String name = orderItem.getProduct(); 
			Product product = productMap.get(name);
			if (product==null) {
				isValid = false; 
				motivation += "Il prodotto " + name + " non esiste. ";
				// break; 
			} else if (product.getStockLevel() < orderItem.getQuantity()) {
				isValid = false; 
				motivation += "Il prodotto " + name + " non è disponibile nella quantità richiesta. ";
				// break; 
			}
		}
		if (motivation.length()==0) {
			motivation = "OK"; 
		}
		return new OrderValidation(id, order, isValid, motivation);
	}

	private List<String> toProductNames(List<OrderItem> items) {
		List<String> names = 
			items.stream()
				.map(item -> item.getProduct())
				.collect(Collectors.toList());
		return names; 
	}

	private Map<String,Product> toProductMap(List<Product> products) {
		Map<String,Product> map = 
			products.stream() 
				.collect(Collectors.toMap(Product::getName, Function.identity()));
		return map;
	}

}
