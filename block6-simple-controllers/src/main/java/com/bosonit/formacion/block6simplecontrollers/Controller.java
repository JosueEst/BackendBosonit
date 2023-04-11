package com.bosonit.formacion.block6simplecontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controller {

    @GetMapping(value= "/user/{nombre}")
    public String user(@PathVariable (required = false) Optional <String> nombre){
        return nombre.isPresent() ? "Hola: "+nombre.get() : "Sin nombre" ;

    }

    @PostMapping (value="/useradd")
    public ResponseEntity <Persona> useradd (@RequestBody Persona p ){
        p.setAge(String.valueOf(Integer.parseInt(p.getAge())+1));
        return new ResponseEntity<>(p, HttpStatus.OK);

    }
}
