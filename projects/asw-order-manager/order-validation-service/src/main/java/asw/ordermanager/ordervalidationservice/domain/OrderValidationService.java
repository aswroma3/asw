package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.stereotype.Service;

@Service 
public interface OrderValidationService {

	/* Verifica la validità di un ordine. */ 
	public OrderValidation validateOrder(Long id); 

}
