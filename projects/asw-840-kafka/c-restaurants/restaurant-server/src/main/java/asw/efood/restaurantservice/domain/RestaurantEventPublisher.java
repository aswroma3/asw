package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 

public interface RestaurantEventPublisher {

    public void publish(DomainEvent event);

}
