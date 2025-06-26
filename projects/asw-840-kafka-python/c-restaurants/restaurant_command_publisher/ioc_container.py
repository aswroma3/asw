from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_command_publisher.domain.RestaurantCommandPublisherRunner import RestaurantCommandPublisherRunner 
from restaurant_kafka_utils.SimpleKafkaMessageProducer import SimpleMessageProducer

# command publisher 
command_publisher_name = 'Python Restaurant Command Publisher'
command_channel = 'restaurant-service-command-channel'

class Container(containers.DeclarativeContainer):

    simple_message_producer_provider = providers.Singleton(
                SimpleMessageProducer, 
                name = command_publisher_name, 
                channel = command_channel) 
    restaurant_command_publisher_runner_provider = providers.Singleton(
                RestaurantCommandPublisherRunner, 
                simple_message_producer = simple_message_producer_provider) 

