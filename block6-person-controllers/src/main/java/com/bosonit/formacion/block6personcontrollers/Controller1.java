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


    //Method to set headers and body of a ResponseEntity object. Then return it;

    @GetMapping (value = "/addPersona")
    public ResponseEntity<Person> addPersona (@RequestParam Map<String,String> params){

        HttpHeaders headers = new HttpHeaders();
        headers.add("name",params.get("name"));
        headers.add("location",params.get("location"));
        headers.add("location",params.get("age"));

        service1.person.setName(params.get("name"));
        service1.person.setLocation(params.get("location"));
        service1.person.setAge(params.get("age"));

        return new ResponseEntity<>(service1.person, headers, HttpStatus.OK);
    }

    //Method to add a City object type to a City's list

    @PostMapping (value = "/addCiudad")
    public List<City> addCiudad (@RequestParam String name, @RequestParam int nHab){

        service1.cityList.add(new City (name,nHab));
        return service1.returnCityList();
    }
    @GetMapping (value = "/bean/{bean1}")
    public void returnBean (@PathVariable Person p){

    }
}
