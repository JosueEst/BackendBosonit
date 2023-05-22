package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.application.PersonService;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorOutputDto;
import com.bosonit.formacion.block7crudvalidation.feign.MyFeign;
import feign.Feign;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping ("/person")
@CrossOrigin (origins = "https://cdpn.io",methods= {RequestMethod.GET,RequestMethod.POST})
public class PersonController {
    @Autowired
    PersonService personService;

    //TODO: PROBAR TODOS LOS CRUD Y MIRAR LOS CASCADE

    //Método para, en función de del parámetro pasado ('simple' -por defecto- o 'full') nos devolverá un objeto Persona (simple)
    //o un objeto Persona con un join con la tabla Estudiantes/Profesores. Es decir, la info de Persona y además la de su rol.
    @GetMapping ("/{id}")
    public ResponseEntity getPersonById
            (@PathVariable int id, @RequestParam (defaultValue="simple") String outputType) throws EntityNotFoundException{
        try{

            if(outputType.equals("full")){
                return ResponseEntity.ok().body(personService.getPersonFull(id));

            }else{
                return ResponseEntity.ok().body(personService.getPersonById(id));
            }

        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Person with id '"+id+"' not found");
        }
    }
    //Mismo método pero buscando Persona por nombre
    @GetMapping ("/name")
    public ResponseEntity getPersonByName
            (@RequestParam String name, @RequestParam (defaultValue = "simple") String outputType){
        try {
            if(outputType.equals("full")){
                return ResponseEntity.ok().body(personService.getPersonFull(name));
            }
            return ResponseEntity.ok().body(personService.getPersonByName(name));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping ("/getall")
    public ResponseEntity <List> getAllPersons (@RequestParam (defaultValue = "simple") String outputType){
        try {
            if(outputType.equals("full")){

                List<Object> personList =new ArrayList<>();
                for(PersonOutputDto personOutputDto : personService.getAllPersons()){
                    personList.add(personService.getPersonFull(personOutputDto.getId_person()));
                }
                return ResponseEntity.ok().body(personList);
            }
            return ResponseEntity.ok().body(personService.getAllPersons());
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("No existen personas en la base de datos");
        }
    }
    //Endpoint para obtner un profesor por id usando RestTemplate
    @GetMapping ("/profesor/{id}")
    public ResponseEntity<ProfessorOutputDto> getProfessorTemp (@PathVariable int id){
        ResponseEntity <ProfessorOutputDto> professorOutputDtoResponseEntity=
                new RestTemplate().getForEntity("http://localhost:8081/professor/"+id, ProfessorOutputDto.class);
        return professorOutputDtoResponseEntity;
    }
    //Usando FEIGN - no deja usar ResponseEntity
    @GetMapping ("/profesor/feign/{id}")
    public String getProfessor (@PathVariable int id){
        MyFeign myFeign = Feign.builder().target(MyFeign.class, "http://localhost:8081/professor/"+id);
        return myFeign.getProfessor();
    }

    //Return an 'UnprocessEntityException' in case a field is not valid
    //@CrossOrigin(origins = "https://cdpn.io")//, methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping ("/addPerson")
    public ResponseEntity<PersonOutputDto> addPerson (@RequestBody PersonInputDto personInputDto)
            throws UnprocessableEntityException {
        try{
            URI location = URI.create("/persona");
            return ResponseEntity.created(location).body(personService.addPerson(personService.validation (personInputDto)));
        }catch(UnprocessableEntityException e){
            throw new UnprocessableEntityException (e.getMessage());
        }
    }
    @PutMapping ("/{id}")
    public ResponseEntity <PersonOutputDto> updatePersonById (@PathVariable int id, @RequestBody PersonInputDto personInputDto)
            throws EntityNotFoundException, UnprocessableEntityException {
        try {
            return ResponseEntity.ok().body(personService.updatePersonById(id, personService.validation (personInputDto)));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Object with id '"+id+"' not found");
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException (e.getMessage());
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <String>  deletePersonById (@PathVariable int id) throws EntityNotFoundException{
        try{
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id '"+id+"' has been deleted");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("404: Object with id '"+id+"' not found");
        }
    }


    //Method for catching 'EntityNotFoundException' exceptions
    @ExceptionHandler (EntityNotFoundException.class)
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
