package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.subject.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.subject.SubjectOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Student;
import com.bosonit.formacion.block7crudvalidation.domain.Subject;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import com.bosonit.formacion.block7crudvalidation.repository.SubjectsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectsRepository subjectsRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) {
        return subjectsRepository.save(new Subject(subjectInputDto)).subjectsToSubjectsOutputDto();
    }

    @Override
    public SubjectOutputDto getSubjectById(int id) {
        return subjectsRepository.findById(id).orElseThrow(EntityNotFoundException::new).subjectsToSubjectsOutputDto();
    }

    @Override
    public SubjectOutputDto updateSubjectById(int id_subject, SubjectInputDto subjectInputDto) {
        subjectsRepository.findById(id_subject).orElseThrow(EntityNotFoundException::new);
        Subject subject = new Subject(subjectInputDto);
        subject.setId_subject(subjectsRepository.findById(id_subject).orElseThrow(EntityNotFoundException::new).getId_subject());
        return subjectsRepository.save(subject).subjectsToSubjectsOutputDto();
    }

    @Override
    public void deleteSubjectById(int id) {
        subjectsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        subjectsRepository.deleteById(id);
    }

    @Override
    public List<SubjectOutputDto> getAllSubjects() {

        return subjectsRepository.findAll().stream().map(Subject::subjectsToSubjectsOutputDto).toList();
    }
    @Override
    public List<SubjectOutputDto> getAllStudentSubjects(int id_student){
        Student student = studentRepository.findById(id_student).orElseThrow(EntityNotFoundException::new);
        List<Subject> subjectList = student.getSubjects();
        return subjectList.stream().map(Subject::subjectsToSubjectsOutputDto).toList();
    }

    //Método para asignar SÓLO UNA asigntura a un Estudiante
    @Override
    public void addSubjectToStudent(int id_student, int id_subject) {
        Student student = studentRepository.findById(id_student).orElseThrow();
        Subject subject = subjectsRepository.findById(id_subject).orElseThrow();

        List<Subject> list = student.getSubjects();
        list.add(subject);
        student.setSubjects(list);
        //subject.getStudents().add(student);
        studentRepository.save(student);
    }

    //Método para ñadir varias asignaturas a la vez a un Estudiante.
    // La lista la pasamos con objetos Integer y no con int por si hay valores null
    @Override
    public void addManySubjectsToStudent(int id_student, List<Integer> id_subjects) {
        Student student = studentRepository.findById(id_student).orElseThrow();

        //Recorre las asignaturas de la BD y
        //luego compara el id de las asignaturas del estudiante con las del repositorio de asignaturas
        for (Subject s : subjectsRepository.findAllById(id_subjects)){
            if(!student.getSubjects().stream().anyMatch(subject -> subject.getId_subject()==s.getId_subject()))
                student.getSubjects().add(s);
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteSubjectToStudent(int id_student, List<Integer> id_subjects) {
        Student student = studentRepository.findById(id_student).orElseThrow();

        if(student.getSubjects().size() ==0){
            throw new EntityNotFoundException();
        }
        for (Subject s : subjectsRepository.findAllById(id_subjects)){
            if(student.getSubjects().stream().anyMatch(subject -> subject.getId_subject()==s.getId_subject()))
                student.getSubjects().remove(s);
        }
        studentRepository.save(student);
    }

    @Override
    public SubjectOutputDto addSubjectToProfessor(SubjectInputDto subjectInputDto) {
        return null;
    }
}
