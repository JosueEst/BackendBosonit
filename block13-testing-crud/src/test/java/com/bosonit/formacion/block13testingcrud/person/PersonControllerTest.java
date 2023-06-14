package com.bosonit.formacion.block13testingcrud.person;

import com.bosonit.formacion.block13testingcrud.Exceptions.CustomError;
import com.bosonit.formacion.block13testingcrud.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.application.PersonServiceImpl;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block13testingcrud.controller.PersonController;
import com.bosonit.formacion.block13testingcrud.domain.Person;
import com.bosonit.formacion.block13testingcrud.domain.PersonMapper;
import com.bosonit.formacion.block13testingcrud.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerTest {

    @Mock
    PersonRepository personRepository;
    @Mock
    PersonServiceImpl personService;
    @InjectMocks
    PersonController personController;
    private Person person;
    private PersonInputDto personInputDto;
    private PersonOutputDto personOutputDto;
    private List<PersonOutputDto> personOutputDtoList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        personInputDto = new PersonInputDto();
        personInputDto.setUsuario("usuario1");
        personInputDto.setPassword("password1");
        personInputDto.setName("Josue");
        personInputDto.setSurname("Esteban");
        personInputDto.setCompanyEmail("j@gmail.com");
        personInputDto.setPersonalEmail("j@gmail.com");
        personInputDto.setCity("Burgos");
        personInputDto.setActive(true);
        personInputDto.setCreatedDate(new Date(2023, Calendar.JULY,8));

        person = new Person();
        person.setId(personInputDto.getId());
        person.setUsuario(personInputDto.getUsuario());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompanyEmail(personInputDto.getCompanyEmail());
        person.setPersonalEmail(personInputDto.getPersonalEmail());
        person.setCity(personInputDto.getCity());
        person.setActive(personInputDto.getActive());
        person.setCreatedDate(personInputDto.getCreatedDate());

        personOutputDto = PersonMapper.INSTANCE.personToPersonOutputDto(person);

        personOutputDtoList.add(personOutputDto);
    }

    @Test
    void addPersonTest() throws Exception {
        //when(personService.addPerson(personService.validation(personInputDto))).thenReturn(personOutputDto);

        ResponseEntity<PersonOutputDto> personOutputDtoResponseEntity = personController.addPerson(personInputDto);

        assertEquals(HttpStatus.CREATED, personOutputDtoResponseEntity.getStatusCode());

    }
    @Test
    void getPersonById (){
        when(personService.getPersonById(1)).thenReturn(personOutputDto);
        ResponseEntity<PersonOutputDto> personOutputDtoResponseEntity= personController.getPersonById(1);
        assertEquals(HttpStatus.OK, personOutputDtoResponseEntity.getStatusCode());

//        EntityNotFoundException entityNotFoundException =
//                assertThrows(EntityNotFoundException.class, () -> personController.getPersonById(3));
//        assertEquals("404: Object with id '"+3+"' not found", entityNotFoundException.getMessage());
    }

    @Test
    void getPersonByName (){
        when(personService.getPersonByName("Josue")).thenReturn(personOutputDto);
        ResponseEntity<PersonOutputDto> personOutputDtoResponseEntity =
                personController.getPersonByName("Josue");
        assertEquals(HttpStatus.OK, personOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void getAllPersons (){
        when(personService.getAllPersons()).thenReturn(personOutputDtoList);
        Iterable<PersonOutputDto> personOutputDtoIterable = personController.getAllPersons();
        assertEquals( 1, List.of(personOutputDtoIterable).size());
    }
    @Test
    void updatePersonByIdTest () throws UnprocessableEntityException {
        personInputDto.setName("Alba");
        when(personService.updatePersonById(1,personService.validation(personInputDto))).thenReturn(personOutputDto);

        ResponseEntity<PersonOutputDto> personOutputDtoResponseEntity =
                personController.updatePersonById(1, personInputDto);
        assertEquals(HttpStatus.OK, personOutputDtoResponseEntity.getStatusCode());

    }
    @Test
    void deletePersonById (){
        //when(personService.deletePersonById(1)).thenReturn("Person with id '"+1+"' has been deleted");
        ResponseEntity<String> responseEntity = personController.deletePersonById(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Person with id '"+1+"' has been deleted", responseEntity.getBody());
    }

}
