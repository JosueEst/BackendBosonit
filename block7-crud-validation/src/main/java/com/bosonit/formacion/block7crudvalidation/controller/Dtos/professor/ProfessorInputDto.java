package com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorInputDto {

    private int id_professor;

    private String comments;

    private String branch;

    private int id_person;
}
