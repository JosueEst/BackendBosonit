package com.bosonit.formacion.configuration;
import com.bosonit.formacion.events.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración de un productor en Spring Boot para poder enviar datos vía Apache Kafka
 */
@Configuration
public class KafkaProducerConfig {

    private final String bootstrapAddress = "localhost:9092";

    @Bean
    public ProducerFactory<String, Event<?>> producerFactory()  {
        Map<String, Object> configProperties = new HashMap<>();
        // direccion del bus de mensajes de Kafka
        configProperties.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        // Tipo de clave con la que se va a serializar el mensaje
        configProperties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Serializamos el valor en sí del mensaje como un JSON
        configProperties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProperties);
    }

    /**
     * Clase dentro de Apache Kafka que nos permite utilizar
     * los métodos para 'publicar' datos hacia el bus de mensajes. KafkaTemplate
     * necesita la configuración de ProducerFactory para enviar mensajes.
     * @return new KafkaTemplate<Object, Object>;
     */
    @Bean
    public KafkaTemplate <String, Event <?>> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
