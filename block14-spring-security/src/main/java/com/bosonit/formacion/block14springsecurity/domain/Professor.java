package com.bosonit.formacion.block14springsecurity.domain;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorFullOutputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorInputDto;
import com.bosonit.formacion.block14springsecurity.controller.Dtos.professor.ProfessorOutputDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "Profesores")
public class Professor {
    @Id
    @GeneratedValue
    @Column (name = "id_profesor")
    int id_professor;
    @OneToOne
    @JoinColumn (name="id_personaFK", nullable = false, unique = true)
    Person person;
    @Column(name="comentarios")
    String comments;
    @Column(name = "rama")
    String branch;
    @OneToMany (mappedBy = "professor")
    List<Subject> subjects;
    @OneToMany (mappedBy = "professor")
    List<Student> students;


    public Professor (ProfessorInputDto professorInputDto){
        this.id_professor=professorInputDto.getId_professor();
        this.comments=professorInputDto.getComments();
        this.branch=professorInputDto.getBranch();
    }

    public ProfessorOutputDto  professorToProfessorOutputDto(){
        return new ProfessorOutputDto (this.id_professor,this.comments,this.branch, this.person.getId_person());
    }
    public ProfessorFullOutputDto professorToProfessorFullOutputDto(){
        return new ProfessorFullOutputDto (
                this.id_professor,
                this.comments,
                this.branch,
                this.person.getId_person(),
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
