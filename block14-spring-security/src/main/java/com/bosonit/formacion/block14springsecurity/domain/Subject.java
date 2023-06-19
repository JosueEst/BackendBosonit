package com.bosonit.formacion.block14springsecurity.domain;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.subject.SubjectOutputDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "Asignaturas")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_subject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Professor professor;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "subjects")
    private List<Student> students;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "comentarios")
    private String comment;
    @Column(name = "initial_date")
    private Date initial_date;
    @Column(name = "finish_date")
    private Date finish_date;

    public Subject(SubjectInputDto subjectInputDto){
        this.id_subject=subjectInputDto.getId_subject();
        this.asignatura=subjectInputDto.getAsignatura();
        this.comment=subjectInputDto.getComment();
        this.initial_date=subjectInputDto.getInitial_date();
        this.finish_date=subjectInputDto.getFinish_date();
    }
    public SubjectOutputDto subjectsToSubjectsOutputDto (){
        return new SubjectOutputDto(this.id_subject,
                this.asignatura,
                this.comment,
                this.initial_date,
                this.finish_date);
    }

}
