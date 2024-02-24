package asw.efood.restaurantservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	@Query("SELECT r " +
            "FROM Restaurant r LEFT JOIN FETCH r.menu.menuItems " +
            "WHERE r.id = :id " 
            )
	public Restaurant findByIdWithMenu(@Param("id") Long id);

 	public Restaurant findByName(String name); 
	
	public Collection<Restaurant> findAll();
	
	public Collection<Restaurant> findAllByLocation(String location);

}

