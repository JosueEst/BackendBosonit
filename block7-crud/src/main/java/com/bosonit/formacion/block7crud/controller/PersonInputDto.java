package com.bosonit.formacion.block7crud.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDto {
    int id;
    @NotEmpty(message = "El nombre es obligatorio")//No permite valor nulo pero si vacío
    String name;
    @NotEmpty (message = "La edad es obligatoria")
    String age;
    @NotEmpty (message = "La población es obligatoria")
    String location;
}
