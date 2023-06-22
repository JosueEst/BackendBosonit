package com.bosonit.formacion.configuration;

import com.bosonit.formacion.events.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private final String bootstrapAddress = "localhost:9092";
    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory(){
        Map<String, String > properties = new HashMap<>();
        //ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG:
        // direcciones de los servidores de Kafka a los que el consumidor se conectará.
        properties.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        //Le decimos a kafka que confiamos en esta clase para 'deserializar' un mensaje
        properties.put(JsonSerializer.TYPE_MAPPINGS, "com.bosonit.formacion:com.bosonit.formacion.events.CustomerCreatedEvent" );

        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer<>();
        return new DefaultKafkaConsumerFactory(
                properties,
                new StringDeserializer(),
                jsonDeserializer);
    }

    /** Bean necesario que hay que asociar a cada consumidor en sí. Es el elemento que
     * nos va a permitir estar escuchando los mensajes que se escriben el el bus de mensajes (flujo de algún topic)
     * y poder leerlos de manera sencilla.
     * @return ConcurrentKafkaListenerContainerFactory <K, V>
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory <String, Event<?>>
    kafkaListenerContainerFactory(){

        //Este listener va a oir mensajes tipo 'Event <?>'
        ConcurrentKafkaListenerContainerFactory  <String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
