package com.bosonit.formacion.service;

import com.bosonit.formacion.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.listener.ConsumerAwareRebalanceListener.LOGGER;

@Slf4j
@Service
public class ProducerService {
    private final ProducerEventsService producerEventsService;

    public ProducerService (ProducerEventsService producerEventsService){
        super();
        this.producerEventsService = producerEventsService;
    }
    public Customer saveCustomer (Customer customer){
        log.info("RECIBIDO: " + customer);
        this.producerEventsService.publish(customer);
        return  customer;
    }
}
