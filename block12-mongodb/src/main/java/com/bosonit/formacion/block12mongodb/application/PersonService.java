package com.bosonit.formacion.block12mongodb.application;

import com.bosonit.formacion.block12mongodb.controller.PersonInputDto;
import com.bosonit.formacion.block12mongodb.controller.PersonOutputDto;
import com.bosonit.formacion.block12mongodb.exceptions.UnprocessableEntityException;

import java.util.List;

public interface PersonService {

    //Retrieve a person by id
    PersonOutputDto getPersonById (String id);
    //Retrieve a person by name
    PersonOutputDto getPersonByName (String name);
    //Retrieve all persons of database
    List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
    //Method to add a person
    PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException;
    //Method to update person data
    PersonOutputDto updatePersonById (String id, PersonInputDto personInputDto) throws UnprocessableEntityException;
    //Delete a person
    void deletePersonById (String id);



}
