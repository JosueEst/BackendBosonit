package com.bosonit.formacion.block14springsecurity.domain;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.person.PersonFullProfessorOutputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.person.PersonFullStudentOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Personas")
public class Person {

    @Id
    @GeneratedValue
    @Column (name="id_persona")
    private int id_person;
    @Column (name="usuario")
    private String usuario;
    @Column (name="contrasena")
    private String password;
    @Column (name="nombre")
    private String name;
    @Column (name="apellido")
    private String surname;
    @Column (name="email_empresa")
    private String companyEmail;
    @Column (name="email_personal")
    private String personalEmail;
    @Column (name="ciudad")
    private String city;
    @Column (name="activo")
    private Boolean active;
    @Column (name="fecha_creacion")
    private Date createdDate;
    @Column (name="imagen_url")
    private String imageUrl;
    @Column (name="fecha_terminacion")
    private Date terminationDate;

    @OneToOne (mappedBy = "person",cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private Professor professor;

    //cascade = CascadeType.ALL / orphanRemoval = true  -> da problemas al intentar actualizar un estudiante
    @OneToOne (mappedBy = "person",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private Student student;

    @Column(name = "admin", nullable = false)
    private Boolean admin;

    public PersonFullProfessorOutputDto personToPersonFullProfessorOutputDto (){
        return new PersonFullProfessorOutputDto(
                this.id_person,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate,
                this.professor.professorToProfessorOutputDto()
        );
    }
    public PersonFullStudentOutputDto personToPersonFullStudentOutputDto (){
        return new PersonFullStudentOutputDto(
                this.id_person,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate,
                this.student.studentToStudentOutputDto()
        );
    }

}
