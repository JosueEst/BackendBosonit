package com.bosonit.formacion.block6pathvariableheaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Controller {

    //Se le pasa un objeto body JSON y lo devuelve
    @PostMapping (value = "/postMethod")
    public ResponseEntity<String> postMethod (@RequestBody String body){
        return new ResponseEntity<>(body, HttpStatus.OK);

    }
    //Se le pasa un parámetro en el path y lo devuelve
    @GetMapping (value = "/user/{id}")
    public String sendId (@PathVariable (value = "id") String valor){
        return "El ID es: "+valor;
    }
    //Se le pasan variables en un HashMap y los devuelve en formato String
    @PutMapping (value = "/putMethod")
    public String putMethod (@RequestParam HashMap<String,String> params){
        return params.toString();

    }

}
