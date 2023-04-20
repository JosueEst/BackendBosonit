package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.PersonMapper;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException {
        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }

    @Override
    public PersonOutputDto getPersonById(int id)  {
        //INSTANCE -> static property to access PersonMapper's methods
        try{
           return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }
    @Override
    public PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto){
        personInputDto.setId(id);
        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }
    @Override
    public void deletePersonById (int id){
        personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personRepository.deleteById(id);
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
    public PersonInputDto validation (PersonInputDto personInputDto) throws UnprocessableEntityException {
        if(personInputDto.getUsuario()==null) throw new UnprocessableEntityException ("The username mustn't be null");
        else if (personInputDto.getUsuario().length() <6 || personInputDto.getUsuario().length() > 10)
            throw new UnprocessableEntityException("Username length must be between 6 and 10 characters");
        else if (personInputDto.getPassword() == null) throw new UnprocessableEntityException ("The password mustn't be null");
        else if (personInputDto.getName() == null) throw new UnprocessableEntityException("The name mustn't be null");
        else if (personInputDto.getCompanyEmail() == null) throw new UnprocessableEntityException("Company email mustn't be null");
        else if (personInputDto.getPersonalEmail() == null) throw new UnprocessableEntityException("Personal email mustn't be null");
        else if (personInputDto.getCity() == null) throw new UnprocessableEntityException("City mustn't be null");
        else if (personInputDto.getActive() == null) throw new UnprocessableEntityException("Property 'active' must be 'true' or 'false'");
        else if (personInputDto.getCreatedDate() == null) throw new UnprocessableEntityException("'CreatedDate' mustn't be null");
        else log.info("Datos validados correctamente");
        return personInputDto;
    }
}
