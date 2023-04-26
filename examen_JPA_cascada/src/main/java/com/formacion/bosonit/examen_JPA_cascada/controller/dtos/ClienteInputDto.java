package com.formacion.bosonit.examen_JPA_cascada.controller.dtos;

import com.formacion.bosonit.examen_JPA_cascada.domain.CabeceraFra;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInputDto {
    //int id;
    @Size (max = 100)
    String nombre;
    List <CabeceraFra> facturas;



}
