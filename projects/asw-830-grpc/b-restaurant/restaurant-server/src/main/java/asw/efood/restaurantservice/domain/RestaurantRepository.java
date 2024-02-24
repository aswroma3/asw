package asw.efood.restaurantservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

 	public Restaurant findByName(String name); 
	
	public Collection<Restaurant> findAll();
	
	public Collection<Restaurant> findAllByLocation(String location);

}

