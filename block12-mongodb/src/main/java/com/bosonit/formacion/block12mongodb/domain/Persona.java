package com.bosonit.formacion.block12mongodb.domain;

import com.bosonit.formacion.block12mongodb.controller.PersonInputDto;
import com.bosonit.formacion.block12mongodb.controller.PersonOutputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document (collection="personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @MongoId
    String id;
    String name;
    String surname;
    String age;
    String location;

    public Persona(PersonInputDto personInputDto){

        this.name=personInputDto.getName();
        this.surname=personInputDto.getSurname();
        this.age=personInputDto.getAge();
        this.location=personInputDto.getLocation();
    }

    public PersonOutputDto personToPersonOutputDto (){
        return new PersonOutputDto(
                this.id,
                this.name,
                this.surname,
                this.age,
                this.location
        );
    }

}
