package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.StudentService;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.*;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent (@RequestBody StudentInputDto studentInputDto)
            throws SQLIntegrityConstraintViolationException, EntityNotFoundException {
        try{
            URI location = URI.create("/student");
            return ResponseEntity.ok().body(studentService.addStudentToPerson(studentInputDto));
        }catch(SQLIntegrityConstraintViolationException e){
            throw new SQLIntegrityConstraintViolationException ("Esa persona ya tiene asignada un perfil de estudiante o de profesor");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
    //Método para devolver un Estudiante (@RequestParam="simple")
    // o un Estudiante junto con la info de Persona (@RequestParam ="full")
    @GetMapping("/{id}")
    public ResponseEntity getStudentById (@PathVariable int id, @RequestParam (defaultValue="simple") String outputType )
            throws EntityNotFoundException {
        try{
            if(outputType.equals("full")){
                return ResponseEntity.ok().body(studentService.getStudentByIdLikeNativeFull(id));
            }else{
                return ResponseEntity.ok().body(studentService.getStudentById(id));
            }
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Student with id '"+id+"' not found");
        }
    }
    @GetMapping
    public Iterable<StudentOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return studentService.getAllStudents(pageNumber, pageSize);
    }
    //Method to update a student
    @PutMapping ("/{id}")
    public ResponseEntity <StudentOutputDto> updateStudentById (@PathVariable int id, @RequestBody StudentInputDto studentInputDto)
            throws EntityNotFoundException{
        try{
            return ResponseEntity.ok().body(studentService.updateStudentById(id, studentInputDto));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Student with id '"+studentInputDto.getId_student()+"' not found");
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity <String>  deleteStudentById (@PathVariable int id) throws EntityNotFoundException{
        try{
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("Student with id '"+id+"' has been deleted");
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("404: Student with id '"+id+"' not found");
        }
    }

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<CustomErrorOutputDto> handleEntityNotFoundException (EntityNotFoundException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.NOT_FOUND.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorOutputDto);
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
