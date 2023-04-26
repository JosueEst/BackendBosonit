package com.formacion.bosonit.examen_JPA_cascada.controller.dtos;

import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.domain.LineasFra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraFraInputDto {

    //int id;
    double importeFra;
    Cliente cli_codi;
    //int cli_codi;
    List<LineasFra> lineasFraList;


}
