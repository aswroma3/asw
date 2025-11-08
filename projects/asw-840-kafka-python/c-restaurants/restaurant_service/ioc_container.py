from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_service.domain.RestaurantService import RestaurantService 
from restaurant_service.domain.RestaurantCommandHandler import RestaurantCommandHandler 
from restaurant_service.event_publisher.RestaurantEventPublisher import RestaurantEventPublisher
from restaurant_service.command_listener.RestaurantCommandListener import RestaurantCommandListener
from restaurant_kafka_utils.SimpleKafkaMessageProducer import SimpleMessageProducer
from restaurant_kafka_utils.SimpleKafkaMessageConsumer import SimpleMessageConsumer

# event publisher 
event_publisher_name = 'Python Restaurant Event Publisher'
event_channel = 'restaurant-service-event-channel'

# command listener 
command_listener_name = 'Python Restaurant Command Listener'
command_channel = 'restaurant-service-command-channel'
command_listener_group_id = 'restaurant-command-listener'

class Container(containers.DeclarativeContainer):

    simple_message_producer_provider = providers.Singleton(
                SimpleMessageProducer, 
                name = event_publisher_name, 
                channel = event_channel) 
    restaurant_event_publisher_provider = providers.Singleton(
                RestaurantEventPublisher, 
                simple_message_producer = simple_message_producer_provider) 
    restaurant_service_provider = providers.Singleton(
                RestaurantService, 
                restaurant_event_publisher = restaurant_event_publisher_provider) 
    restaurant_command_handler_provider = providers.Singleton(
                RestaurantCommandHandler, 
                restaurant_service = restaurant_service_provider) 
    restaurant_command_listener_provider = providers.Singleton(
                RestaurantCommandListener, 
                restaurant_command_handler = restaurant_command_handler_provider) 
    simple_message_consumer_provider = providers.Singleton(
                SimpleMessageConsumer, 
                simple_consumer_service = restaurant_command_listener_provider, 
                name = command_listener_name, 
                channel = command_channel, 
                group_id = command_listener_group_id) 
