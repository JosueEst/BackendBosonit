package com.bosonit.formacion.block7crudvalidation.controller.Dtos.student;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.subject.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.subject.SubjectOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    private int id_student;
    private int id_person;
    private int num_hours_week;
    private String comments;
    private String branch;
    List<Subject> subjects;
    private int id_profesor;

}
