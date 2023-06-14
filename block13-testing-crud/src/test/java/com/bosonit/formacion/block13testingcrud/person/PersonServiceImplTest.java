package com.bosonit.formacion.block13testingcrud.person;

import com.bosonit.formacion.block13testingcrud.Exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.application.PersonServiceImpl;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonInputDto;
import com.bosonit.formacion.block13testingcrud.controller.Dtos.PersonOutputDto;
import com.bosonit.formacion.block13testingcrud.domain.Person;
import com.bosonit.formacion.block13testingcrud.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private PersonRepository personRepository;
    private PersonInputDto personInputDto;

    private PersonOutputDto personOutputDto;


    @BeforeAll
    public void setUp (){
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
        personInputDto.setImageUrl("www.image.com");
        personInputDto.setTerminationDate(new Date(2023,Calendar.JULY,20));

    }

    @Test
    @Order(1)
    void addPersonTest () throws UnprocessableEntityException {
        personOutputDto = personService.addPerson(personInputDto);

        assertEquals("usuario1", personOutputDto.getUsuario());
        assertEquals("password1", personOutputDto.getPassword());
        assertEquals("Josue", personOutputDto.getName());
        assertEquals("Esteban", personOutputDto.getSurname());
        assertEquals("j@gmail.com", personOutputDto.getPersonalEmail());
        assertEquals("j@gmail.com", personOutputDto.getCompanyEmail());
        assertEquals("Burgos", personOutputDto.getCity());
        assertEquals(true, personOutputDto.getActive());
        assertEquals(new Date(2023, Calendar.JULY,8), personOutputDto.getCreatedDate());
        assertEquals("www.image.com", personOutputDto.getImageUrl());
        assertEquals(new Date(2023, Calendar.JULY,20), personOutputDto.getTerminationDate());


        PersonInputDto personInputDto1 = new PersonInputDto();
        assertThrows(UnprocessableEntityException.class, () ->personService.addPerson(personInputDto1));

    }

    @Test
    @Order(2)
    void getPersonByIdTest () throws UnprocessableEntityException {

        personOutputDto = personService.getPersonById(1);
        assertEquals("usuario1", personOutputDto.getUsuario());
        assertEquals("Esteban", personOutputDto.getSurname());

        EntityNotFoundException entityNotFoundException =
                assertThrows(EntityNotFoundException.class, () -> personService.getPersonById(3));
        assertEquals("Usuario no encontrado", entityNotFoundException.getMessage());
        assertEquals(1, personOutputDto.getId());
    }
    @Test
    @Order(3)
    void getPersonByName () throws EntityNotFoundException {
        personOutputDto = personService.getPersonByName("Josue");

        assertEquals("Josue", personOutputDto.getName());

        EntityNotFoundException entityNotFoundException =
                assertThrows(EntityNotFoundException.class, () -> personService.getPersonByName("Manuel"));
        assertEquals("Usuario no encontrado", entityNotFoundException.getMessage());
    }
    @Test
    @Order(4)
    void getAllPersonsTest() {
        List<PersonOutputDto> personOutputDtoList = personService.getAllPersons();

        assertNotNull(personOutputDtoList);
        assertEquals(1, personOutputDtoList.size());
    }
    @Test
    @Order(5)
    void updatePersonByIdTest (){
        PersonInputDto personInputDto2 = personInputDto;
        personInputDto2.setName("Alba");
        personInputDto2.setSurname("García");
        personService.updatePersonById(1, personInputDto2);

        assertEquals("Alba", personInputDto.getName());
        assertEquals("García", personInputDto.getSurname());
    }
    @Test
    @Order(6)
    void deletePersonByIdTest (){

        personService.deletePersonById(1);
        List<PersonOutputDto> personOutputDtoList = personService.getAllPersons();
        assertEquals(new ArrayList<>(), personOutputDtoList);
        assertEquals(0, personOutputDtoList.size());

        EntityNotFoundException entityNotFoundException =
                assertThrows(EntityNotFoundException.class, () -> personService.deletePersonById(4));
        assertEquals("Usuario no encontrado", entityNotFoundException.getMessage());
    }
    @Test@Order(7)
    void validationPersonInputDtoTest (){
        PersonInputDto personInputDto2 = new PersonInputDto();

        UnprocessableEntityException unprocessableEntityException = assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): The username mustn't be null", unprocessableEntityException.getMessage());

        personInputDto2.setUsuario("UsuarioMuyLargo");
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): Username length must be between 6 and 10 characters",
                unprocessableEntityException.getMessage());

        personInputDto2.setUsuario("Corto");
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): Username length must be between 6 and 10 characters",
                unprocessableEntityException.getMessage());

        personInputDto2.setUsuario("Usuario");
        personInputDto2.setPassword(null);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): The password mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setPassword("Password");
        personInputDto2.setName(null);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): The name mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setName("Nombre");
        personInputDto2.setCompanyEmail(null);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): Company email mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setCompanyEmail("email@gmail.com");
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): Personal email mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setPersonalEmail("email@gmail.com");
        personInputDto2.setCity(null);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): City mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setCity("Burgos");
        personInputDto2.setActive(null);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): Property 'active' must be 'true' or 'false'",
                unprocessableEntityException.getMessage());

        personInputDto2.setActive(true);
        unprocessableEntityException =
                assertThrows(UnprocessableEntityException.class, () -> personService.validation(personInputDto2));
        assertEquals("422 (UNPROCESSABLE ENTITY): 'CreatedDate' mustn't be null",
                unprocessableEntityException.getMessage());

        personInputDto2.setCreatedDate(new Date());

        assertDoesNotThrow(() -> personService.validation(personInputDto2), String.valueOf(UnprocessableEntityException.class));
    }
    @Test
    void handleEntityNotFoundException (){
        ResponseEntity<CustomErrorOutputDto> responseEntity=personService.handleEntityNotFoundException(new EntityNotFoundException());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(CustomErrorOutputDto.class, Objects.requireNonNull(responseEntity.getBody()).getClass());
        assertEquals(new CustomErrorOutputDto().getMessage(), responseEntity.getBody().getMessage());
        assertEquals(new Date().toString(), responseEntity.getBody().getTimestamp().toString());
    }
    @Test
    void handleUnprocessableEntityException (){
        ResponseEntity<CustomErrorOutputDto> responseEntity = personService.handleUnprocessableEntityException(new UnprocessableEntityException());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    }
}
