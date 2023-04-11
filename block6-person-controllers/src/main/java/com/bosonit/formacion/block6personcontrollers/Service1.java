package com.bosonit.formacion.block6personcontrollers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Data
public class Service1 {

    Person p ;

    @Bean
    public Person returnPerson(){
        p = new Person();
        return p;
    }
}
