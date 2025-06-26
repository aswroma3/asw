from dependency_injector import containers, providers
#from dependency_injector.wiring import Provide, inject

from restaurant_client_grpc.restaurant_service_client_grpc.RestaurantServiceGrpcClient import RestaurantServiceGrpcClient 
from restaurant_client_grpc.domain.RestaurantClientRunner import RestaurantClientRunner

class Container(containers.DeclarativeContainer):

    restaurant_service_client_provider = providers.Singleton(
                RestaurantServiceGrpcClient) 
    restaurant_client_runner_provider = providers.Singleton(
                RestaurantClientRunner, 
                restaurant_service_client = restaurant_service_client_provider) 

