package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.application.PersonServiceImpl;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping ("/person")
public class Controller {
    @Autowired
    PersonServiceImpl personService;

    @GetMapping ("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById (@PathVariable int id) throws EntityNotFoundException{
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Object with id '"+id+"' not found");
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
    //Return an 'UnprocessEntityException' in case a field is not valid
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson (@RequestBody PersonInputDto personInputDto)
            throws UnprocessableEntityException {
        try{
            URI location = URI.create("/persona");
            return ResponseEntity.created(location).body(personService.addPerson(personService.validation (personInputDto)));
        }catch(UnprocessableEntityException e){
            throw new UnprocessableEntityException (e.getMessage());
        }
    }
    @PutMapping ("/{id}")
    public ResponseEntity <PersonOutputDto> updatePersonById (@PathVariable int id, @RequestBody PersonInputDto personInputDto)
            throws EntityNotFoundException, UnprocessableEntityException {
        try {
            return ResponseEntity.ok().body(personService.updatePersonById(id, personService.validation (personInputDto)));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Object with id '"+id+"' not found");
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException (e.getMessage());
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity <String>  deletePersonById (@PathVariable int id) throws EntityNotFoundException{
        try{
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id '"+id+"' has been deleted");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("404: Object with id '"+id+"' not found");
        }
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons (){
        return personService.getAllPersons();
    }

    //Method for catching 'EntityNotFoundException' exceptions
    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<CustomErrorOutputDto> handleEntityNotFoundException (EntityNotFoundException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.NOT_FOUND.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorOutputDto);
    }
    @ExceptionHandler (UnprocessableEntityException.class)
    public ResponseEntity<CustomErrorOutputDto> handleUnprocessableEntityException (UnprocessableEntityException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customErrorOutputDto);
    }

}
