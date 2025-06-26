from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_server.domain.RestaurantService import RestaurantService 
from restaurant_server.grpc_server.RestaurantGrpcServer import RestaurantGrpcServer

class Container(containers.DeclarativeContainer):

    restaurant_service_provider = providers.Singleton(
                RestaurantService) 
    restaurant_grpc_server_provider = providers.Singleton(
                RestaurantGrpcServer, 
                restaurant_service = restaurant_service_provider) 

