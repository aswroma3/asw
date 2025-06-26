from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_server.domain.RestaurantService import RestaurantService 

class Container(containers.DeclarativeContainer):

    restaurant_service_provider = providers.Singleton(
                RestaurantService) 

