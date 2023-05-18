package com.bosonit.formacion.block10dockerizeapp.application;

import com.bosonit.formacion.block10dockerizeapp.controller.PersonInputDto;
import com.bosonit.formacion.block10dockerizeapp.controller.PersonOutputDto;
import com.bosonit.formacion.block10dockerizeapp.domain.Persona;
import com.bosonit.formacion.block10dockerizeapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson (PersonInputDto personInputDto){
        return personRepository.save(new Persona(personInputDto)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto) {
        //getPersonById(id).setName(personInputDto.getName());
        personInputDto.setId(id);
        return personRepository.save(new Persona(personInputDto)).personToPersonOutputDto();
    }
    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }
    @Override
    public List<PersonOutputDto> getAllPersons(){

        return personRepository.findAll().stream().map(Persona::personToPersonOutputDto).toList();
    }
    @Override
    public PersonOutputDto getPersonById (int id){
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonByName(String name) {
        return personRepository.findByName(name).personToPersonOutputDto();
    }

}
