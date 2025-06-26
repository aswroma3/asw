#
# Simple Kafka Producer 
#
# Simple perché è semplice. 
# E' in grado di inviare messaggi json con un tipo 
#

from confluent_kafka import Producer
import json

# parameters 

bootstrap_servers = 'localhost:9092' 
# 1 vuol dire che ogni messaggio viene inviato da solo, quindi subito (ok per gli esperimenti) 
batch_num_messages = 1 

class SimpleMessageProducer: 
    def __init__(self, name, channel): 
        self.producer = Producer({'bootstrap.servers': bootstrap_servers, 'batch.num.messages': batch_num_messages})
        self.producer_name = name
        self.channel = channel 

    def delivery_report(self, err, msg):
        """ Called once for each message produced to indicate delivery result.
            Triggered by poll() or flush(). """
        if err is not None:
            print('{} message delivery failed: {}'.format(self.producer_name, err))
        #else:
        #    print('{} sent message {} to {} [{}]'.format(self.producer_name, msg.value(), msg.topic(), msg.partition()))

    def publish(self, record, record_type): 
        # Trigger any available delivery report callbacks from previous produce() calls
        self.producer.poll(0)
        
        # print('{} sending message: {} {} to {}'.format(self.producer_name, record, record_type, self.channel))
        # se record è una stringa  
        # message = json.dumps(record)
        # se record è un oggetto pydantic 
        message = record.json()
        headers = { "__TypeId__": record_type }
        self.producer.produce(self.channel, message, headers=headers, callback=self.delivery_report)
        
    def close(self): 
        # Wait for any outstanding messages to be delivered and delivery report callbacks to be triggered.
        self.producer.flush()
