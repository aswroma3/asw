from pydantic import BaseModel

class DomainEvent(BaseModel):
    pass

class RestaurantCreatedEvent(DomainEvent):
    id: int 
    name: str
    location: str 

restaurant_created_event_type = 'asw.efood.restaurantservice.api.event.RestaurantCreatedEvent'

