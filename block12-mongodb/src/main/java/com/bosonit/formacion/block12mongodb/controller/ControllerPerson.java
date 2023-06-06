package com.bosonit.formacion.block12mongodb.controller;

import com.bosonit.formacion.block12mongodb.application.PersonService;
import com.bosonit.formacion.block12mongodb.application.PersonServiceImpl;

import com.bosonit.formacion.block12mongodb.exceptions.CustomErrorOutputDto;
import com.bosonit.formacion.block12mongodb.exceptions.EntityNotFoundException;
import com.bosonit.formacion.block12mongodb.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping ("/person")
public class ControllerPerson {

    @Autowired
    PersonService personService;

    //Method to retrieve a person by id
    @GetMapping ("{id}")
    public ResponseEntity<PersonOutputDto> getPersonById (@PathVariable String id){
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
    //Method to retrieve all persons
    @GetMapping
    public Iterable<PersonOutputDto> getAllPersonsPaginated (@RequestParam int pageNumber,@RequestParam int pageSize){
        return personService.getAllPersons(pageNumber, pageSize);
    }

    //Adding a person giving data in the body
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson ( @RequestBody PersonInputDto personInputDto) throws UnprocessableEntityException {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(personInputDto));
    }

    //Method to update a person giving an id and the properties in a body
    @PutMapping ("/{id}")
    public ResponseEntity <PersonOutputDto> updatePersonById (@PathVariable String id, @RequestBody PersonInputDto personInputDto ){
        try {
            return ResponseEntity.ok().body(personService.updatePersonById(id, personInputDto));
        }catch (EntityNotFoundException | UnprocessableEntityException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
    //Method for deleting a person giving an id by a PathVariable
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletePersonById (@PathVariable String id){
        try{
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id '"+id+"' has been deleted");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    //Method to catch 'EntityNotFoundException' exceptions and so it to the client
    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<CustomErrorOutputDto> handleEntityNotFoundException (EntityNotFoundException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.NOT_FOUND.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorOutputDto);
    }
    //Method to catch 'UnprocessableEntityException' exceptions and so it to the client
    @ExceptionHandler (UnprocessableEntityException.class)
    public ResponseEntity<CustomErrorOutputDto> handleUnprocessableEntityException (UnprocessableEntityException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customErrorOutputDto);
    }
}
