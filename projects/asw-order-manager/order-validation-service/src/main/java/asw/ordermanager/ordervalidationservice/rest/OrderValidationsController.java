package asw.ordermanager.ordervalidationservice.rest;

import asw.ordermanager.ordervalidationservice.domain.*; 

import org.springframework.web.bind.annotation.*; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant; 
import java.time.Duration; 

import java.util.*; 

import java.util.logging.Logger; 

@RestController
public class OrderValidationsController {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
	private OrderValidationService orderValidationService;

	/* Verifica se l'ordine con id è valido. */ 
	@GetMapping("/ordervalidations/{id}")
	public OrderValidation validateOrder(@PathVariable Long id) {
		Instant start = Instant.now();
		logger.info("REST CALL: validateOrder " + id); 
		OrderValidation orderValidation = orderValidationService.validateOrder(id); 
		Duration duration = Duration.between(start, Instant.now()); 
		logger.info("validateOrder " + id + " (duration " + duration.toMillis() + " ms): " + orderValidation);
		return orderValidation; 
	}
	
}
