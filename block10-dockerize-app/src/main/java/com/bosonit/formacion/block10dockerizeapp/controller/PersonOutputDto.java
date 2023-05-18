package com.bosonit.formacion.block10dockerizeapp.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    int id;
    String name;
    String age;
    String location;
}
