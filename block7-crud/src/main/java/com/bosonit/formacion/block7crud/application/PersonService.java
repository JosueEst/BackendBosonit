package com.bosonit.formacion.block7crud.application;

import com.bosonit.formacion.block7crud.controller.PersonInputDto;
import com.bosonit.formacion.block7crud.controller.PersonOutputDto;

import java.util.List;

public interface PersonService {

    //Method to add a person
    PersonOutputDto addPerson (PersonInputDto personInputDto);


    //Method to update person data
    PersonOutputDto updatePersonById (int id, PersonInputDto personInputDto);
    //Delete a person
    void deletePersonById (int id);

    //Retrieve all persons of database
    List<PersonOutputDto> getAllPersons();
    //Retrieve a person by id
    PersonOutputDto getPersonById (int id);
    //Retrieve a person by name
    PersonOutputDto getPersonByName (String name);
}
