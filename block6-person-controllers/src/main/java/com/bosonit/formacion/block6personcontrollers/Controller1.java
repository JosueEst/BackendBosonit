package com.bosonit.formacion.block6personcontrollers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping (value = "/controller1")
@Data
public class Controller1 {
    @Autowired
    @Qualifier ("returnPerson")
    Person person;//Referencia al @Bean de la clase @Service

    /*@GetMapping (value = "/addPersona")
    @Bean
    public Person addPersona (@RequestHeader Map <String, String> cabeceras){
        person.setName(cabeceras.get("name"));
        person.setLocation(cabeceras.get("location"));
        person.setAge(cabeceras.get("age"));
        return person;
    }*/

    @Bean
    @GetMapping (value = "/addPersona")
    public ResponseEntity<Person> addPersona (@RequestParam Map<String,String> params){

        HttpHeaders headers = new HttpHeaders();
        headers.add("name",params.get("name"));
        headers.add("location",params.get("location"));
        headers.add("location",params.get("age"));
        person.setName(params.get("name"));
        person.setLocation(params.get("location"));
        person.setAge(params.get("age"));

        return new ResponseEntity<>(person, headers, HttpStatus.OK);
    }


}
