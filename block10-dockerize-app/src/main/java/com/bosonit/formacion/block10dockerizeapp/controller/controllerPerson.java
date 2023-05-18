package com.bosonit.formacion.block10dockerizeapp.controller;

import com.bosonit.formacion.block10dockerizeapp.application.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/person")
public class controllerPerson {

    @Autowired
    PersonServiceImpl personService;

    //Adding a person giving data in the body
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson (@Valid @RequestBody PersonInputDto personInputDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(personInputDto));
    }

    //Method to update a person giving an id and the properties in a body
    @PutMapping ("/{id}")
    public ResponseEntity <PersonOutputDto> updatePersonById (@PathVariable int id, @RequestBody PersonInputDto personInputDto ){
        try {
            return ResponseEntity.ok().body(personService.updatePersonById(id, personInputDto));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //Method for deleting a person giving an id by a PathVariable
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletePersonById (@PathVariable int id){
        try{
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id '"+id+"' has been deleted");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //Method to retrieve all persons
    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons (){
        return personService.getAllPersons();
    }
    //Method to retrieve a person by id
    @GetMapping ("{id}")
    public ResponseEntity<PersonOutputDto> getPersonById (@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personService.getPersonById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //Method to retrieve a person by name
    @GetMapping ("/name/{name}")
    public ResponseEntity<PersonOutputDto> getPersonByName (@PathVariable String name){
        try {
            return ResponseEntity.ok().body(personService.getPersonByName(name));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
