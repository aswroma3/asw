#
# Hello REST Server 
#

from fastapi import FastAPI

from hello_service.HelloService import hello_service

app = FastAPI()

@app.get("/hello/{name}")
def say_hello(name: str):
    return hello_service.sayHello(name)
#    return "Hello, {}!".format(name)

        
@app.get("/")
def get_root():
    return "Hello, World!"

@app.get("/hello")
def get_hello():
    return "Hello!"

@app.get("/hello/")
def get_hello_slash():
    return "Hello, Slash!"

