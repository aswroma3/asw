from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_client_rest.restaurant_service_client_rest.RestaurantServiceRestClient import RestaurantServiceRestClient 
from restaurant_client_rest.domain.RestaurantClientRunner import RestaurantClientRunner

class Container(containers.DeclarativeContainer):

    restaurant_service_client_provider = providers.Singleton(
                RestaurantServiceRestClient) 
    restaurant_client_runner_provider = providers.Singleton(
                RestaurantClientRunner, 
                restaurant_service_client = restaurant_service_client_provider) 

