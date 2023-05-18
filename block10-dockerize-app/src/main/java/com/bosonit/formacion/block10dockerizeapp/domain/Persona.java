package com.bosonit.formacion.block10dockerizeapp.domain;

import com.bosonit.formacion.block10dockerizeapp.controller.PersonInputDto;
import com.bosonit.formacion.block10dockerizeapp.controller.PersonOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    String age;

    String location;

    public Persona(PersonInputDto personInputDto){
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
