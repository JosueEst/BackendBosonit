package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.PersonMapper;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson (PersonInputDto personInputDto) throws Exception {

        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(validation(personInputDto));
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }
    //TODO: probar este metodo y terminar el resto
    @Override
    public PersonOutputDto getPersonById(int id) throws Exception {
        //INSTANCE -> static property to access PersonMapper's methods
             return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findById(id).orElseThrow());
    }

    @Override
    public PersonOutputDto getPersonByName(String name) {
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findByName(name).orElseThrow());
    }

    @Override
    public List<PersonOutputDto> getAllPersons() {
        //Same as --> .map(it -> PersonMapper.INSTANCE::personToPersonOutputDto(it))
        return personRepository.findAll().stream().map(PersonMapper.INSTANCE::personToPersonOutputDto).toList();
    }

    @Override
    public PersonInputDto validation (PersonInputDto personInputDto) throws Exception {
        if(personInputDto.getUsuario()==null) throw new Exception ("The username mustn't be null");
        else if (personInputDto.getUsuario().length() <6 || personInputDto.getUsuario().length() > 10)
            throw new Exception("Username length must be between 6 and 10 characters");
        else if (personInputDto.getPassword() == null) throw new Exception ("The password mustn't be null");
        else if (personInputDto.getName() == null) throw new Exception("The name mustn't be null");
        else if (personInputDto.getCompanyEmail() == null) throw new Exception("Company email mustn't be null");
        else if (personInputDto.getPersonalEmail() == null) throw new Exception("Personal email mustn't be null");
        else if (personInputDto.getCity() == null) throw new Exception("City mustn't be null");
        else if (personInputDto.getActive() == null) throw new Exception("Property 'active' must be 'true' or 'false'");
        else if (personInputDto.getCreatedDate() == null) throw new Exception("'CreatedDate' mustn't be null");
        else log.info("Datos validados correctamente");
        return personInputDto;
    }
}
