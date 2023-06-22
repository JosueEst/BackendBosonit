package com.bosonit.formacion.controller;

import com.bosonit.formacion.entity.Customer;
import com.bosonit.formacion.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/producer")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService){
        super();
        this.producerService=producerService;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody  Customer customer){
        return this.producerService.saveCustomer(customer);
    }
}
