package com.bosonit.formacion.service;

import com.bosonit.formacion.events.CustomerCreatedEvent;
import com.bosonit.formacion.entity.Customer;
import com.bosonit.formacion.events.Event;

import com.bosonit.formacion.events.EventType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ProducerEventsService {

    private final KafkaTemplate <String, Event<?>> producer;

    public ProducerEventsService(KafkaTemplate <String, Event<?>> producer) {
        this.producer=producer;
    }

    @Value(value = "${message.topic.name:supertopic}")
    private String topic;

    /**
     * Método para publicar mensajes en un topic de kafka
     * @param customer
     */
    public void publish (Customer customer){

        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(customer);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());
        //Pasamos al objeto KafkaTemplate ya configurado en la clase '[...]config'
        //el 'topic' y el mensaje con metadatos
        this.producer.send(topic, created);
    }
}
