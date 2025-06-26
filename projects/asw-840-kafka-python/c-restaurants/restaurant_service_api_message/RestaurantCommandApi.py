from pydantic import BaseModel

class Command(BaseModel):
    pass 

class CreateRestaurantCommand(Command):
    name: str
    location: str 

create_restaurant_command_type = 'asw.efood.restaurantservice.api.command.CreateRestaurantCommand'


