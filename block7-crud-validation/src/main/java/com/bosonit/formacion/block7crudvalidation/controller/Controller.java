package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonServiceImpl;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/person")
public class Controller {
    @Autowired
    PersonServiceImpl personService;
    @GetMapping ("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById (@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping ("/name")
    public ResponseEntity<PersonOutputDto> getPersonByName (@RequestParam String name){
        try {
            return ResponseEntity.ok().body(personService.getPersonByName(name));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson (@RequestBody PersonInputDto personInputDto) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personService.addPerson(personInputDto));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons (){
        return personService.getAllPersons();
    }
}
