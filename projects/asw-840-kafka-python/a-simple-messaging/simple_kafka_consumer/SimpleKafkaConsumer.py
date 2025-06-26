#
# Simple Kafka Consumer 
#
# Simple perché è in grado solo di ricevere messaggi testuali (stringhe)
#

from confluent_kafka import Consumer

import threading

# parameters 

bootstrap_servers = 'localhost:9092' 
# auto_offset_reset = 'earliest'
auto_offset_reset = 'latest'
default_consumer_timeout = -1.0

class SimpleConsumer: 
    def __init__(self, simple_consumer_service, consumer_name, channel_in, group_id, consumer_timeout=default_consumer_timeout):
        self.simple_consumer_service = simple_consumer_service
        self.consumer_name = consumer_name 
        self.channel_in = channel_in 
        self.group_id = group_id 
        self.consumer_timeout = consumer_timeout
        # auto start
        self.start()

    def start(self):
        # avvia run() in un thread separato 
        t1 = threading.Thread(target=self.run, args=[])
        t1.start()

    def run(self):
        print("Starting Simple Consumer {}".format(self.consumer_name))

        # create consumer and receive messages 

        config = {'bootstrap.servers': bootstrap_servers,
                'group.id': self.group_id,
                'auto.offset.reset': auto_offset_reset}

        consumer = Consumer(config)

        # Process messages asynchronously
        running = True 

        try: 
            consumer.subscribe([self.channel_in])

            while running:
                message = consumer.poll(timeout=self.consumer_timeout)
                if message is None:
                    print("{} exiting".format(self.consumer_name))
                    break
                if message.error():
                    print("{} error: {}".format(self.consumer_name, message.error()))
                    continue 
                # message is valid 
                message = message.value().decode('utf-8')
                # message = str(json.loads(message.value()))
                # print("{} received message: {}".format(consumer_name, message))
                ### elabora il messaggio ricevuto ### 
                self.simple_consumer_service.onMessage(message) 
        except Exception as e:
            print("{} an exception occurred: {} ".format(self.consumer_name, e))
        finally:
            # Close down consumer to commit final offsets.
            consumer.close()
