package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PersonService  {


    PersonOutputDto getPersonById (int id) throws EntityNotFoundException;
    //Método para devolver un OutputDto de Persona + sus datos de estudiante
    Object getPersonFull(int id) throws EntityNotFoundException;

    PersonOutputDto getPersonByName (String name);
    //Método para devolver un OutputDto de Persona + sus datos de estudiante o de profesor a partir del nombre de Persona
    Object getPersonFull(String name) throws EntityNotFoundException;


    //Método para devolver todas las persona
    List<PersonOutputDto> getAllPersons();

    PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException;

    PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto);

    void deletePersonById(int id);

    //Método para validar un objeto PersonInputDto
    PersonInputDto validation (PersonInputDto personInputDto) throws UnprocessableEntityException;
}
