package com.bosonit.formacion.block7crud.domain;

import com.bosonit.formacion.block7crud.controller.PersonInputDto;
import com.bosonit.formacion.block7crud.controller.PersonOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Personas")
public class Person {
    @Id
    @GeneratedValue
    int id;

    String name;

    String age;

    String location;

    public Person (PersonInputDto personInputDto){
        this.id= personInputDto.getId();
        this.name=personInputDto.getName();
        this.age=personInputDto.getAge();
        this.location=personInputDto.getLocation();
    }

    public PersonOutputDto personToPersonOutputDto (){
        return new PersonOutputDto(
                this.id,
                this.name,
                this.age,
                this.location
        );
    }

}
