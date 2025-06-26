from pydantic import BaseModel
from typing import List

class GetRestaurantResponse(BaseModel):
    restaurantId: int
    name: str
    location: str 

class GetRestaurantsResponse(BaseModel):
    restaurants: List[GetRestaurantResponse]
    
class CreateRestaurantRequest(BaseModel):
    name: str
    location: str 

class CreateRestaurantResponse(BaseModel):
    restaurantId: int


