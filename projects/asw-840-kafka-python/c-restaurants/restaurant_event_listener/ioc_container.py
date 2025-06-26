from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_event_listener.domain.RestaurantEventListener import RestaurantEventListener 
from restaurant_kafka_utils.SimpleKafkaMessageConsumer import SimpleMessageConsumer

# event listener 
event_listener_name = 'Python Restaurant Event Listener'
event_channel = 'restaurant-service-event-channel'
event_listener_group_id = 'restaurant-event-listener'
#consumer_timeout = 60.0

class Container(containers.DeclarativeContainer):

    restaurant_event_listener_provider = providers.Singleton(
                RestaurantEventListener) 
    simple_message_consumer_provider = providers.Singleton(
                SimpleMessageConsumer, 
                simple_consumer_service = restaurant_event_listener_provider, 
                name = event_listener_name, 
                channel = event_channel, 
                group_id = event_listener_group_id) 

