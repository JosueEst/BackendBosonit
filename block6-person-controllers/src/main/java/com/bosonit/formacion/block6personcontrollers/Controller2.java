package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping (value="/controller2")
public class Controller2 {
    @Autowired
    Service1 service1;


    //GET method to change body's data from de Person object that is sent by addPerson()
    @GetMapping (value = "/getPersona")
    public Person getPersona (){
        service1.person.setAge(String.valueOf(Integer.parseInt(service1.person.getAge())*2));

        return service1.person;
    }
    @GetMapping (value = "/getCiudades")
    public List<City> getCiudades (){

        return service1.cityList;
    }

}
