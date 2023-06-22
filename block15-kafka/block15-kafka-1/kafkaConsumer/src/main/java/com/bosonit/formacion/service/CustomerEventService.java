package com.bosonit.formacion.service;

import com.bosonit.formacion.events.CustomerCreatedEvent;
import com.bosonit.formacion.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Clase para escuchar los mensajes que están llegando a Kafka
 */
@Slf4j
@Component
public class CustomerEventService {

    /**
     * @KafkaListener el una anotación a nivel de método, parametrizable, para configurar un método
     *      de forma que éste pueda estar escuchando ciertos topics de kafka y esperar a que lleguen mensajes
     *      para procesarlos y capturarlos. En este caso los mostrará en tiempo de ejecución por logs de consola.
     * @param event
     */
    // Le pasamos el listener configurado en 'KafkaConsumerConfig' a 'containerFactory'
    @KafkaListener(
            topics = "${message.topic.name:supertopic}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "${spring.kafka.consumer.group-id:myGroup}")
    public void consumer (Event<?> event) {

        if(event.getClass().isAssignableFrom(CustomerCreatedEvent.class)){
            CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;
            log.info("Received Customer created event with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
        }
    }
}
