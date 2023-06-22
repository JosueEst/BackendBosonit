## Block 15 - Kafka
### Nombre proyecto Maven: block12-kafka
### Usando Kafka, montar dos aplicaciones: una que envíe mensajes y la segunda le responda con otros mensajes.

#### Project with two modules connected to a kafka server in Docker.

The module 'kafkaProducer' is made to publish in a topic messages, which are objects of the Customer class. 
This is done through an endpoint of type POST. The other module, 'kafkaConsumer', has a listener that listens to 
what happens in the topic it has created, and every time a message is published, it captures it and displays it 
in the console.

#### The topic that works in the kafka server is created in the docker-compose.yml