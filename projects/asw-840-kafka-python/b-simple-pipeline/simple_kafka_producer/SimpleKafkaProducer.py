#
# Simple Kafka Producer 
#
# Simple perché è in grado solo di inviare messaggi testuali (stringhe)
#

from confluent_kafka import Producer

# parameters 

bootstrap_servers = 'localhost:9092' 
# 1 vuol dire che ogni messaggio viene inviato da solo, quindi subito (ok per gli esperimenti) 
batch_num_messages = 1 

class SimpleProducer: 
    def __init__(self, name, channel_out): 
        self.producer = Producer({'bootstrap.servers': bootstrap_servers, 'batch.num.messages': batch_num_messages})
        self.producer_name = name
        self.channel_out = channel_out 

    def delivery_report(self, err, msg):
        """ Called once for each message produced to indicate delivery result.
            Triggered by poll() or flush(). """
        if err is not None:
            print('{} message delivery failed: {}'.format(self.producer_name, err))
        #else:
        #    print('{} sent message {} to {} [{}]'.format(self.producer_name, msg.value(), msg.topic(), msg.partition()))

    def publish(self, message): 
        # Trigger any available delivery report callbacks from previous produce() calls
        self.producer.poll(0)
        self.producer.produce(self.channel_out, message, callback=self.delivery_report)
        # in ogni caso, pure senza convertire a bytes, il consumatore riceve bytes 
        
    def close(self): 
        # Wait for any outstanding messages to be delivered and delivery report
        # callbacks to be triggered.
        self.producer.flush()
