package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentFullOutputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentOutputDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface StudentService {

    StudentOutputDto addStudentToPerson(StudentInputDto studentInputDto) throws SQLIntegrityConstraintViolationException;

    StudentOutputDto getStudentById(int id);


    //Method to return a left join with Estudiantes table and Personas table
    StudentFullOutputDto getStudentByIdLikeNativeFull(int id);

    StudentOutputDto updateStudentById(int id, StudentInputDto studentInputDto);

    void deleteStudentById(int id);


    List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
}
