package com.bosonit.formacion.block7crudvalidation.controller.Dtos.subject;

import com.bosonit.formacion.block7crudvalidation.domain.Professor;
import com.bosonit.formacion.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutputDto {
    int id_subject;
    String asignatura;
    String comment;
    Date initial_date;
    Date finish_date;
}
