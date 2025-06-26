package asw.efood.restaurantservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	public Collection<Restaurant> findAll();
	
 	public Optional<Restaurant> findByNameAndLocation(String name, String location); 

	public Collection<Restaurant> findAllByLocation(String location);

}

