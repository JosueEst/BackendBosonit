package com.formacion.bosonit.examen_JPA_cascada.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraFraOutputDto {
    int id;
    double importeFra=0;

    ClienteOutputDto clienteOutputDto;
    List<LineasFraOutputDto> lineaOutputDtoList;

}
