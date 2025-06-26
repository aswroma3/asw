#
# Servizio per elaborare i messaggi ricevuti 
#

from simple_kafka_consumer.SimpleKafkaConsumer import SimpleConsumer

# Parameters 

consumer_name = 'Python Consumer'
channel_in = 'asw-alpha'
group_id = 'simple-consumer'

class SimpleConsumerService: 
    def __init__(self): 
        self.simple_consumer = SimpleConsumer(self, consumer_name, channel_in, group_id)

    def onMessage(self, message): 
        print("{} received message: {}".format(consumer_name, message))

#    def start(self): 
#        self.simple_consumer.start()
        
