#
# Servizio per filtrare i messaggi ricevuti 
#

from simple_kafka_consumer.SimpleKafkaConsumer import SimpleConsumer
from simple_kafka_producer.SimpleKafkaProducer import SimpleProducer

# parameters 

filter_name = 'Python Filter'
channel_in = 'asw-alpha'
group_id = 'simple-filter'
channel_out = 'asw-beta'
consumer_timeout = 15.0

class SimpleFilterService: 
    def __init__(self): 
        self.simple_producer = SimpleProducer(filter_name, channel_out) 
        self.simple_consumer = SimpleConsumer(self, filter_name, channel_in, group_id)

    def onMessage(self, message): 
        print("{} received message: {}".format(filter_name, message))
        out_message = "*** " + message + " [filtered by " + filter_name + "] ***" 
        print('{} now sending message: {}'.format(filter_name, out_message))
        self.simple_producer.publish(out_message)        

#    def start(self): 
#        self.simple_consumer.start()
        
