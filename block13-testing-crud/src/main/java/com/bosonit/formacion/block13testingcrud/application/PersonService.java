package com.bosonit.formacion.block13testingcrud.application;

import com.bosonit.formacion.block13testingcrud.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException;

    PersonOutputDto getPersonById (int id) throws EntityNotFoundException;

    PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto);

    void deletePersonById(int id);

    PersonOutputDto getPersonByName (String name);
    List<PersonOutputDto> getAllPersons ();
    PersonInputDto validation (PersonInputDto personInputDto) throws UnprocessableEntityException;
}
