package com.bosonit.formacion.block12mongodb.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDto {

    String name;
    String surname;
    String age;
    String location;
}
