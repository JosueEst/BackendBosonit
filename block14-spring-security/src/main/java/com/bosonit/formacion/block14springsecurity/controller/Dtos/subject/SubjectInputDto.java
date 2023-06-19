package com.bosonit.formacion.block14springsecurity.controller.Dtos.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInputDto {

    int id_subject;
    String asignatura;
    String comment;
    Date initial_date;
    Date finish_date;
}
