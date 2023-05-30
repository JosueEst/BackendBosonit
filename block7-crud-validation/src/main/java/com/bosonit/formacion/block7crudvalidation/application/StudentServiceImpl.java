package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.Professor;
import com.bosonit.formacion.block7crudvalidation.domain.Student;
import com.bosonit.formacion.block7crudvalidation.repository.Person.PersonRepository;
import com.bosonit.formacion.block7crudvalidation.repository.ProfessorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProfessorRepository professorRepository;

    //Método al que le llega un studentInputDto (con las propiedades proias de Student + el id_persona);
    //Buscamos la persona con el id_persona que porta studentInputDto y creamos un objeto Student a partir del input
    //del parámetro (hay un constructor personalizado en Student).
    // Finalmente hacemos los setters correspondiente a esta relación OneToOne y devolvemos al cliente un studentOutputDto
    @Override
    public StudentOutputDto addStudentToPerson(StudentInputDto studentInputDto) throws SQLIntegrityConstraintViolationException {
        Person person = personRepository.findById(studentInputDto.getId_person()).orElseThrow(EntityNotFoundException::new);
        Professor professor = professorRepository.findById(studentInputDto.getId_profesor()).orElseThrow(EntityNotFoundException::new);
        if (person.getStudent() == null && person.getProfessor() == null) {
            Student student = new Student(studentInputDto);
            //person.setStudent(student);
            student.setPerson(person);
            student.setProfessor(professor);
            return studentRepository.save(student).studentToStudentOutputDto();
        } else {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    //Method to return a only a simple object of Student
    @Override
    public StudentOutputDto getStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new).studentToStudentOutputDto();
    }

    //Method to return a left join with Estudiantes table and Personas table
    @Override
    public StudentFullOutputDto getStudentByIdLikeNativeFull(int id) {
        return studentRepository.getStudentByIdLikeNativeFull(id).studentToStudentFullOutputDto();
    }
    //// pageNumber = nº de página; cada página son 10 registros | pageSize = number of objects to retrieve
    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        //Same as --> .map(it -> PersonMapper.INSTANCE::personToPersonOutputDto(it))
        return studentRepository.findAll(pageRequest).getContent().stream().map(Student::studentToStudentOutputDto).toList();
    }

    @Override
    public StudentOutputDto updateStudentById(int id, StudentInputDto studentInputDto) {
        Person person = personRepository.findById(studentInputDto.getId_person()).orElseThrow(EntityNotFoundException::new);
        studentInputDto.setId_student(id);
        Student student = new Student(studentInputDto);
        //person.setStudent(student);
        student.setPerson(person);
        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        studentRepository.deleteById(id);
    }


}
