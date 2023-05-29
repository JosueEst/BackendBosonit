package com.bosonit.formacion.block7crudvalidation.controller.Dtos.person;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorOutputDto;
import com.bosonit.formacion.block7crudvalidation.controller.Dtos.student.StudentOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonFullStudentOutputDto {
    private int id_person;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private LocalDate createdDate;
    private String imageUrl;
    private LocalDate terminationDate;
    StudentOutputDto studentOutputDto;
}
