package com.bosonit.formacion.block6personcontrollers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (value = "/controller1")
@Data
public class Controller1 {


   @Autowired
   Service1 service1;

   @Autowired
   Main main;

   @Autowired
   @Qualifier ("personBean1")
   Person person1;

   @Autowired
   @Qualifier ("personBean2")
   Person person2;
   @Autowired
   @Qualifier ("personBean3")
   Person person3;


    //Method to set headers and body of a ResponseEntity object. Then return it;

    @GetMapping (value = "/addPersona")
    public ResponseEntity<Person> addPersona (@RequestParam Map<String,String> params){

        HttpHeaders headers = new HttpHeaders();
        headers.add("name",params.get("name"));
        headers.add("location",params.get("location"));
        headers.add("location",params.get("age"));

        service1.getPerson().setName(params.get("name"));
        service1.getPerson().setLocation(params.get("location"));
        service1.getPerson().setAge(params.get("age"));

        return new ResponseEntity<>(service1.getPerson(), headers, HttpStatus.OK);
    }

    //Method to add a City object type to a City's list

    @PostMapping (value = "/addCiudad")
    public List<City> addCiudad (@RequestParam String name, @RequestParam int nHab){

        service1.cityList.add(new City (name,nHab));
        return service1.returnCityList();
    }
    @GetMapping (value = {"/bean","/bean/{bean}"})
    public Person returnBean (@PathVariable (required = false) String bean){
        switch (bean){
            case "bean1" : return person1;
            case "bean2" : return person2;
            case "bean3" : return person3;
            default:
                return  new Person("Sin nombre","Sin ciudad", "Sin edad");
        }
    }

}
