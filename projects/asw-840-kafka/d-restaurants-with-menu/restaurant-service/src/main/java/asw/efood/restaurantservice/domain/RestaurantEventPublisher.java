package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 

public interface RestaurantEventPublisher {

    void publish(DomainEvent event);

}
