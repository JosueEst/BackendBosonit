package com.bosonit.formacion.block14springsecurity.controller.Dtos.student;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullOutputDto {
    private int id_student;
    private int num_hours_week;
    private String comments;
    private String branch;
    private int id_person;

    List <SubjectOutputDto> subjects;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;
}
