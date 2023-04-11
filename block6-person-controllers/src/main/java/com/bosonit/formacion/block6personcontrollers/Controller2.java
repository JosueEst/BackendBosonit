package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping (value="/controller2")
public class Controller2 {
    // 1
    @Autowired
    @Qualifier ("addPersona")
    ResponseEntity<Person> aP ;

    // 2
    /*@Autowired
    @Qualifier ("addPersona")
    Person p;*/


    @GetMapping (value = "/getPersona")
    public Person getPersona (){
        Person p;
         if(aP.hasBody() ){
            p =  aP.getBody();
            p.setAge(String.valueOf(Integer.parseInt(p.getAge())*2));
        }else{
             p = new Person();
        }
        return p;
    }

    // 2
    /*@GetMapping (value = "/getPersona")
    public Person  getPersona (){

        p.setAge(String.valueOf(Integer.parseInt(p.getAge())*2));

        return p;
    }*/

}
