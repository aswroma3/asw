package asw.ordermanager.orderservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface OrderRepository extends CrudRepository<Order, Long> {

	public Collection<Order> findAll();

	public Collection<Order> findByCustomer(String customer);

	public Collection<Order> findByOrderItems_Product(String product);

}

