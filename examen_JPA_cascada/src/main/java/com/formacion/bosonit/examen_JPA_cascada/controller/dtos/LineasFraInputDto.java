package com.formacion.bosonit.examen_JPA_cascada.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasFraInputDto {

    String proNomb;
    double cantidad;
    double precio;
    int idFra;//id de factura
}
