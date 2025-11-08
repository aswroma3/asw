package asw.efood.restaurantservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	Collection<Restaurant> findAll();
	
 	Optional<Restaurant> findByNameAndLocation(String name, String location); 

	Collection<Restaurant> findAllByLocation(String location);

}

