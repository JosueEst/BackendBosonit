package com.bosonit.formacion.block13testingcrud.controller;

import com.bosonit.formacion.block13testingcrud.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.application.PersonService;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping ("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping ("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById (@PathVariable int id) {

            return ResponseEntity.ok().body(personService.getPersonById(id));
    }
    @GetMapping ("/name")
    public ResponseEntity<PersonOutputDto> getPersonByName (@RequestParam String name){
            return ResponseEntity.ok().body(personService.getPersonByName(name));
    }
    //Return an 'UnprocessEntityException' in case a field is not valid
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson (@RequestBody PersonInputDto personInputDto) throws UnprocessableEntityException {

                 URI location = URI.create("/persona");
                 return ResponseEntity.created(location).body(personService.addPerson(personInputDto));
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
    public ResponseEntity <String>  deletePersonById (@PathVariable int id){
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id '"+id+"' has been deleted");
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons (){
        return personService.getAllPersons();
    }

}
