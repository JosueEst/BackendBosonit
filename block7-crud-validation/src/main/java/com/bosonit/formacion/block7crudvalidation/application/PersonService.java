package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson (PersonInputDto personInputDto) throws Exception;

    PersonOutputDto getPersonById (int id) throws Exception;

    PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto);

    void deletePersonById(int id);

    PersonOutputDto getPersonByName (String name);
    List<PersonOutputDto> getAllPersons ();
    PersonInputDto validation (PersonInputDto personInputDto) throws Exception;
}
