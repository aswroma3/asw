#
# Simple Kafka Message Consumer 
#
# Simple perché è semplice. 
# E' in grado di ricevere messaggi json con un tipo 
#

from confluent_kafka import Consumer
import json

from restaurant_service_api_message.RestaurantMessageConverter import RestaurantMessageConverter

import threading

# parameters 

bootstrap_servers = 'localhost:9092' 
# auto_offset_reset = 'earliest'
auto_offset_reset = 'latest'
default_consumer_timeout = -1.0

class SimpleMessageConsumer: 
    def __init__(self, simple_consumer_service, name, channel, group_id, consumer_timeout=default_consumer_timeout, auto_start = True):
        self.simple_consumer_service = simple_consumer_service
        self.name = name 
        self.channel = channel 
        self.group_id = group_id 
        self.consumer_timeout = consumer_timeout
        if auto_start: 
            self.start()

    def message_type(self, message): 
        headers = message.headers()
        message_type = None 

        for header in headers: 
            if header[0] == '__TypeId__': 
                message_type = header[1].decode('utf-8')
                break 
        return message_type 

    def short_type(self, my_type): 
        return my_type[my_type.rfind('.')+1:]
        
    def start(self):
        # avvia run() in un thread separato 
        t1 = threading.Thread(target=self.run, args=[])
        t1.start()

    def run(self):
        # deve essere avviato in un thread separato, perché non termina mai 
        print("Starting Simple Consumer {}".format(self.name))

        # create consumer and receive messages 

        config = {'bootstrap.servers': bootstrap_servers,
                'group.id': self.group_id,
                'auto.offset.reset': auto_offset_reset}

        consumer = Consumer(config)

        # Process messages asynchronously
        running = True 

        try: 
            consumer.subscribe([self.channel])

            while running:
                message = consumer.poll(timeout=self.consumer_timeout)
                if message is None:
                    print("{} exiting".format(self.name))
                    break
                if message.error():
                    print("{} error: {}".format(self.name, message.error()))
                    continue 
                # message is valid 
                # headers = message.headers()
                record_type = self.message_type(message) 
                json_data = message.value().decode('utf-8')
                record = RestaurantMessageConverter.json_to_object(json_data, record_type)
                #record = RestaurantMessageConverter.message_to_object(message)
                # record = str(json.loads(message.value()))
                # print("{} received message: {} {}".format(self.name, self.short_type(record_type), record)) 
                ### elabora il messaggio ricevuto ### 
                self.simple_consumer_service.onMessage(record, record_type) 
        except Exception as e:
            print("{} an exception occurred: {} ".format(self.name, e))
        finally:
            # Close down consumer to commit final offsets.
            consumer.close()
