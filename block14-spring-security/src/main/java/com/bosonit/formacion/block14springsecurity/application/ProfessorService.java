package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorFullOutputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorOutputDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface ProfessorService {
    ProfessorOutputDto addProfessorToPerson(ProfessorInputDto professorInputDto) throws SQLIntegrityConstraintViolationException;

    ProfessorOutputDto getProfessorById(int id);


    //Method to return a left join with Profesores table and Personas table
    ProfessorFullOutputDto getProfessorByIdLikeNativeFull(int id);

    ProfessorOutputDto updateProfessorById(int id, ProfessorInputDto professorInputDto);

    void deleteProfessorById(int id);

    List<ProfessorOutputDto> getAllProfessors(int pageNumber, int pageSize);
}
