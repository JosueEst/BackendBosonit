package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.person.PersonInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.person.PersonOutputDto;
import com.bosonit.formacion.block14springsecurity.domain.Person;
import com.bosonit.formacion.block14springsecurity.domain.PersonMapper;
import com.bosonit.formacion.block14springsecurity.domain.Professor;
import com.bosonit.formacion.block14springsecurity.domain.Student;
import com.bosonit.formacion.block14springsecurity.repository.PersonRepository;
import com.bosonit.formacion.block14springsecurity.repository.ProfessorRepository;
import com.bosonit.formacion.block14springsecurity.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) throws UnprocessableEntityException {
        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        //INSTANCE -> static property to access PersonMapper's methods
        try {
            return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    //Método para devolver un OutputDto de Persona + sus datos de estudiante o de profesor a partir del ID de Persona
    @Override
    public Object getPersonFull(int id) throws EntityNotFoundException {
        try {
            Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Student student = person.getStudent();
            Professor professor = person.getProfessor();
            if (professor != null) {
                return personRepository.getPersonFullProfessor(id).personToPersonFullProfessorOutputDto();

            } else if (student != null) {
                return personRepository.getPersonFullStudent(id).personToPersonFullStudentOutputDto();
            } else {
                throw new EntityNotFoundException("Esta persona no tiene asignado un rol aún");
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public PersonOutputDto getPersonByName(String name) {
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.findByName(name).orElseThrow());
    }



    //Método para devolver un OutputDto de Persona + sus datos de estudiante o de profesor a partir del NOMBRE de Persona
    @Override
    public Object getPersonFull(String name) throws EntityNotFoundException {
        try {
            Person person = personRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
            Student student = person.getStudent();
            Professor professor = person.getProfessor();
            if (professor != null) {
                return personRepository.getPersonFullProfessor(name).personToPersonFullProfessorOutputDto();

            } else if (student != null) {
                return personRepository.getPersonFullStudent(name).personToPersonFullStudentOutputDto();
            } else {
                throw new EntityNotFoundException("Esta persona no tiene asignado un rol aún");
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List getAllPersons() {
        //Same as --> .map(it -> PersonMapper.INSTANCE::personToPersonOutputDto(it))
        return personRepository.getAllPersonsFull().stream().map(PersonMapper.INSTANCE::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto) {
        personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personInputDto.setId_person(id);
        Person person = PersonMapper.INSTANCE.personInputDtoToPerson(personInputDto);
        return PersonMapper.INSTANCE.personToPersonOutputDto(personRepository.save(person));
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personRepository.deleteById(id);
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
}
