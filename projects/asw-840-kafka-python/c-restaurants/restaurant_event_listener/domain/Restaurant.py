from pydantic import BaseModel

class Restaurant(BaseModel):
    rid: int
    name: str
    location: str 

    def __str__(self): 
        return "Restaurant(rid=" + str(self.rid) + ", name=" + self.name + ", location=" + self.location + ")"

