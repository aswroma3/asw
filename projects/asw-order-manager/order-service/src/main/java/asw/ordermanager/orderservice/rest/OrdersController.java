package asw.ordermanager.orderservice.rest;

import asw.ordermanager.orderservice.domain.*;

import asw.ordermanager.orderservice.api.rest.*;

import org.springframework.web.bind.annotation.*; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@RestController
public class OrdersController {

	@Autowired 
	private OrderService orderService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Crea un nuovo ordine. */ 
	@PostMapping("/orders")
	public GetOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
		String customer = request.getCustomer();
		String address = request.getAddress();
		List<OrderItem> orderItems = toOrderItems(request.getOrderItems());
		double total = request.getTotal();
		logger.info("REST CALL: createOrder " + customer + ", " + address + ", " + orderItems + ", " + total); 
		Order order = orderService.createOrder(customer, address, orderItems, total);
		GetOrderResponse orderResponse = toOrderResponse(order);
		return orderResponse; 
	}	

	/* Trova l'ordine con id. */ 
	@GetMapping("/orders/{id}")
	public GetOrderResponse getOrder(@PathVariable Long id) {
		logger.info("REST CALL: getOrder " + id); 
		Order order = orderService.getOrder(id);
		if (order==null) {
			logger.info("REST CALL: getOrder " + id + ": Order not found"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Order not found"
			);
		}
		GetOrderResponse orderResponse = toOrderResponse(order);
		logger.info("REST CALL: getOrder " + id + ": " + orderResponse); 
		return orderResponse; 
	}

	/* Trova tutti gli ordini. */ 
	@GetMapping("/orders")
	public Collection<GetOrderResponse> getOrders() {
		Collection<Order> orders = null; 
		Collection<GetOrderResponse> ordersResponse = null; 
		logger.info("REST CALL: getOrders"); 
		orders = orderService.getOrders();
		ordersResponse = toOrdersResponse(orders);
		logger.info("REST CALL: getOrders: " + ordersResponse); 
		return ordersResponse;
	}

	/* Trova tutti gli ordini di un cliente. */ 
	@GetMapping("/findorders/customer/{customer}")
	public Collection<GetOrderResponse> getOrdersByCustomer(@PathVariable String customer) {
		Collection<Order> orders = null; 
		Collection<GetOrderResponse> ordersResponse = null; 
		logger.info("REST CALL: getOrdersByCustomer " + customer); 
		orders = orderService.getOrdersByCustomer(customer);
		ordersResponse = toOrdersResponse(orders);
		logger.info("REST CALL: getOrdersByCustomer " + customer + ": " + ordersResponse); 
		return ordersResponse;
	}

	/* Trova tutti gli ordini per un prodotto. */
	@GetMapping("/findorders/product/{product}")
	public Collection<GetOrderResponse> getOrdersByProduct(@PathVariable String product) {
		Collection<Order> orders = null; 
		Collection<GetOrderResponse> ordersResponse = null; 
		logger.info("REST CALL: getOrdersByProduct " + product); 
		orders = orderService.getOrdersByProduct(product);
		ordersResponse = toOrdersResponse(orders);
		logger.info("REST CALL: getOrdersByProduct " + product + ": " + ordersResponse); 
		return ordersResponse;
	} 

	/* Converte un Order in una GetOrderResponse. */ 
	private GetOrderResponse toOrderResponse(Order order) {
		return new GetOrderResponse(
			order.getId(),
			order.getCustomer(),
			toOrderItemElements(order.getOrderItems()),
			order.getTotal()); 
	}

	/* Converte una collezione di Order in una collezione di GetOrderResponse. */ 
	private Collection<GetOrderResponse> toOrdersResponse(Collection<Order> orders) {
		Collection<GetOrderResponse> ordersResponse = 
			orders
				.stream()
				.map(order -> toOrderResponse(order))
				.collect(Collectors.toList());
		return ordersResponse; 
	}
	
	/* Converte un OrderItemElement in un OrderItem. */ 
	private OrderItem toOrderItem(OrderItemElement item) {
		return new OrderItem(
			item.getProduct(), 
			item.getQuantity()); 
	}

	/* Converte una collezione di OrderItemElement in una collezione di OrderItem. */ 
	private List<OrderItem> toOrderItems(List<OrderItemElement> items) {
		List<OrderItem> orderItems = 
			items
				.stream()
				.map(item -> toOrderItem(item))
				.collect(Collectors.toList());
		return orderItems; 
	}

	/* Converte un OrderItem in un OrderItemElement. */ 
	private OrderItemElement toOrderItemElement(OrderItem item) {
		return new OrderItemElement(
			item.getProduct(), 
			item.getQuantity()); 
	}

	/* Converte una collezione di OrderItem in una collezione di OrderItemElement. */ 
	private List<OrderItemElement> toOrderItemElements(List<OrderItem> items) {
		List<OrderItemElement> orderItems = 
			items
				.stream()
				.map(item -> toOrderItemElement(item))
				.collect(Collectors.toList());
		return orderItems; 
	}

}
