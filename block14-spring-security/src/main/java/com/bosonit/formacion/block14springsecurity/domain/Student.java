package com.bosonit.formacion.block14springsecurity.domain;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentFullOutputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.student.StudentOutputDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Estudiantes")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id_estudiante")
    private int id_student;
    @OneToOne //Borra en cascada la persona a la que referencia
    @JoinColumn(name = "id_personaFK",nullable = false, unique = true)
    private Person person;
    @Column(name = "horas_por_semana")
    private int num_hours_week;
    @Column(name = "comentarios")
    private String comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Professor professor;
    @Column(name = "rama")
    private String branch;
    @ManyToMany (fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public Student (StudentInputDto studentInputDto){
        this.id_student=studentInputDto.getId_student();
        this.num_hours_week=studentInputDto.getNum_hours_week();
        this.comments=studentInputDto.getComments();
        this.branch=studentInputDto.getBranch();
        this.subjects=studentInputDto.getSubjects();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(
                this.id_student,
                this.person.getId_person(),
                this.num_hours_week,
                this.comments,
                this.branch,
                this.subjects,
                this.professor.getId_professor());
    }
    public StudentFullOutputDto studentToStudentFullOutputDto (){
        return new StudentFullOutputDto(
                this.id_student,
                this.num_hours_week,
                this.comments,
                this.branch,
                this.person.getId_person(),
                this.subjects.stream().map(Subject::subjectsToSubjectsOutputDto).toList(),
                this.person.getUsuario(),
                this.person.getPassword(),
                this.person.getName(),
                this.person.getSurname(),
                this.person.getCompanyEmail(),
                this.person.getPersonalEmail(),
                this.person.getCity(),
                this.person.getActive(),
                this.person.getCreatedDate(),
                this.person.getImageUrl(),
                this.person.getTerminationDate()
        );
    }
}

