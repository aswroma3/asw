#
# Simple Producer 
#

from simple_kafka_producer.SimpleKafkaProducer import SimpleProducer

import logging

# parameters 

producer_name = 'Python Producer'
channel_out = 'asw-alpha'
messages_to_send = 3

class SimpleProducerRunner: 
    def run():
        simple_producer = SimpleProducer(producer_name, channel_out) 

        for i in range(messages_to_send): 
            message = "Message #" + str(i) + " from " + producer_name
            print('{} sending message: {}'.format(producer_name, message))
            simple_producer.publish(message)
        simple_producer.close()
