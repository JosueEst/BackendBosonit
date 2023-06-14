package com.bosonit.formacion.block13testingcrud.application;

import com.bosonit.formacion.block13testingcrud.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block13testingcrud.domain.Person;
import com.bosonit.formacion.block13testingcrud.domain.PersonMapper;
import com.bosonit.formacion.block13testingcrud.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    public static final String MENSAJE = "Usuario no encontrado";

    @Override
    public PersonOutputDto addPerson (PersonInputDto personInputDto) throws UnprocessableEntityException {
        try {
            validation(personInputDto);
            Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
            return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
        }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException("Datos de persona no procesables");
        }
    }

    @Override
    public PersonOutputDto getPersonById(int id) throws EntityNotFoundException  {
        //INSTANCE -> static property to access PersonMapper's methods
        try{
           return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(MENSAJE);
        }
    }
    @Override
    public PersonOutputDto getPersonByName(String name) throws EntityNotFoundException{
        try {
            return PersonMapper.INSTANCE
                    .personToPersonOutputDto(personRepository.findByName(name).orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(MENSAJE);
        }
    }

    @Override
    public List<PersonOutputDto> getAllPersons() {
        //Same as --> .map(it -> PersonMapper.INSTANCE::personToPersonOutputDto(it))
        return personRepository.findAll().stream().map(PersonMapper.INSTANCE::personToPersonOutputDto).toList();
    }
    @Override
    public PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto){
        personInputDto.setId(id);
        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }
    @Override
    public void deletePersonById (int id){

        Person person=personRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(MENSAJE));
        personRepository.delete(person);
    }
    @Override
    public PersonInputDto validation(PersonInputDto personInputDto) throws UnprocessableEntityException {
        if (personInputDto.getUsuario() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): The username mustn't be null");
        else if (personInputDto.getUsuario().length() < 6 || personInputDto.getUsuario().length() > 10)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): Username length must be between 6 and 10 characters");
        else if (personInputDto.getPassword() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): The password mustn't be null");
        else if (personInputDto.getName() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): The name mustn't be null");
        else if (personInputDto.getCompanyEmail() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): Company email mustn't be null");
        else if (personInputDto.getPersonalEmail() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): Personal email mustn't be null");
        else if (personInputDto.getCity() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): City mustn't be null");
        else if (personInputDto.getActive() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): Property 'active' must be 'true' or 'false'");
        else if (personInputDto.getCreatedDate() == null)
            throw new UnprocessableEntityException("422 (UNPROCESSABLE ENTITY): 'CreatedDate' mustn't be null");
        else log.info("Datos validados correctamente");
        return personInputDto;
    }
    //Method for catching 'EntityNotFoundException' exceptions
    @ExceptionHandler(EntityNotFoundException.class)
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
