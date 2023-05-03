package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.ProfessorService;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@RestController
@RequestMapping ("/professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorOutputDto> addProfessor (@RequestBody ProfessorInputDto professorInputDto)
            throws  SQLIntegrityConstraintViolationException {
        try{
            URI location = URI.create("/professor");
            return ResponseEntity.created(location).body(professorService.addProfessorToPerson(professorInputDto));
        }catch(SQLIntegrityConstraintViolationException e){
            throw new  SQLIntegrityConstraintViolationException ("Esa persona ya tiene asignada un perfil de estudiante o de profesor");
        }
    }
    //Método para devolver un Estudiante (@RequestParam="simple")
    // o un Estudiante junto con la info de Persona (@RequestParam ="full")
    /*Se pide que devuelva un ResponseEntity sin parametrizar porque dependiendo del QueryParam que se le pase
    al método devolverá un tipo de ResponseEntity */
    @GetMapping("/{id}")
    public ResponseEntity getProfessorById (@PathVariable int id, @RequestParam (defaultValue="simple") String outputType )
            throws EntityNotFoundException {
        try{
            if(outputType.equals("full")){
                return ResponseEntity.ok().body(professorService.getProfessorByIdLikeNativeFull(id));
            }else{
                return ResponseEntity.ok().body(professorService.getProfessorById(id));
            }
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Professor with id '"+id+"' not found");
        }
    }

    //Method to update a professor
    @PutMapping("/{id}")
    public ResponseEntity <ProfessorOutputDto> updateProfessorById (@PathVariable int id, @RequestBody ProfessorInputDto professorInputDto)
            throws EntityNotFoundException {
        try{
            return ResponseEntity.ok().body(professorService.updateProfessorById(id, professorInputDto));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Professor with id '"+professorInputDto.getId_professor()+"' not found");
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <String>  deleteProfessorById (@PathVariable int id) throws EntityNotFoundException{
        try{
            professorService.deleteProfessorById(id);
            return ResponseEntity.ok().body("Professor with id '"+id+"' has been deleted");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("404: Professor with id '"+id+"' not found");
        }
    }
    @GetMapping
    public Iterable<ProfessorOutputDto> getAllProfessors(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return professorService.getAllProfessors(pageNumber, pageSize);
    }
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomErrorOutputDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorOutputDto);
    }
}
