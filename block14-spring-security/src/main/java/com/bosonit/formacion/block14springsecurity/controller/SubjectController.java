package com.bosonit.formacion.block14springsecurity.controller;

import com.bosonit.formacion.block14springsecurity.application.SubjectService;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectOutputDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectOutputDto> addSubject (@RequestBody SubjectInputDto subjectInputDto){
        URI location = URI.create("/subjects");
        return ResponseEntity.created(location).body(subjectService.addSubject(subjectInputDto));
    }
    @GetMapping ("/{id}")
    public ResponseEntity<SubjectOutputDto> getSubjectById (@PathVariable int id){
        try{
            return ResponseEntity.ok().body(subjectService.getSubjectById(id));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Subject with id '"+id+"' not found");
        }
    }
    @PutMapping ("/{id_subject}")
    public ResponseEntity<SubjectOutputDto> updateSubject (@PathVariable int id_subject, @RequestBody SubjectInputDto subjectInputDto){
        try{
            return ResponseEntity.ok().body(subjectService.updateSubjectById(id_subject, subjectInputDto));
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("404: Subject with id '"+subjectInputDto.getId_subject()+"' not found");
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteSubjectById (@PathVariable int id){
        subjectService.deleteSubjectById(id);
        return ResponseEntity.ok().body("Subject with id: '"+id+"' has been deleted");
    }
    //Método para retornar todas las asignaturas de la tabla 'Asignaturas' (Subject entity)
    @GetMapping
    public List<SubjectOutputDto> getAllSubjects (){
        return subjectService.getAllSubjects();
    }

    //Método para retornar las asignaturas de un Estudiante
    @GetMapping ("/student")
    public List<SubjectOutputDto> getAllStudentSubjects(@RequestParam int id_student){
        return subjectService.getAllStudentSubjects(id_student);
    }

    @PostMapping ("/student")
    public ResponseEntity<String> addSubjectToStudent (@RequestParam int id_student, @RequestParam int id_subject){
        try{
            subjectService.addSubjectToStudent(id_student, id_subject);
            return ResponseEntity.ok()
                    .body("Subject with id: '"+id_subject+"' has been added to student with id: '"+id_student+"'");
        }catch (Exception e){
            return ResponseEntity.ok().body("It hasn't been possible to add the subject: "+e.getMessage());
        }

    }

    //Método para añadir varias asignaturas a un estudiante pasando un array de id de Subjects y el id de Student
    @PostMapping ("/student/many")
    public ResponseEntity<String> addManySubjectsToStudent
            (@RequestParam ("id_student") int id_student, @RequestParam ("id_subjects") List<Integer> id_subjects){
        try{
            subjectService.addManySubjectsToStudent(id_student, id_subjects);
            return ResponseEntity.ok().body("Subjects with ids: "+id_subjects.toString()+" have been added");
        }catch (Exception e){
            return ResponseEntity.ok().body("It hasn't been possible to add the subjects: "+e.getMessage());
        }
    }

    //TODO: Continuar con borrar una asignatura a un estudiante mostrando una lista de id
    @DeleteMapping ("/student")
    public ResponseEntity<String> deleteSubjectFromStudent
    (@RequestParam ("id_student") int id_student, @RequestParam ("id_subjects") List<Integer> id_subjects) throws EntityNotFoundException{
        try{
            subjectService.deleteSubjectToStudent(id_student, id_subjects);
            return ResponseEntity.ok().body("Subjects with ids: "+id_subjects.toString()+" have been deleted");
        }catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("El Estudiante con id: '"+id_student+"' no tiene asignaturas asignadas");

        }catch (Exception e){
            return ResponseEntity.ok().body("It hasn't been possible to add the subjects: "+e.getMessage());
        }
    }

}
