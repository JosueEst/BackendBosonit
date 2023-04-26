package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.CabeceraFra;

public interface CabeceraFraService {

    CabeceraFraOutputDto addCabeceraFra (CabeceraFraInputDto cabeceraFraInputDto);

    Iterable<CabeceraFraOutputDto> getAllBills();


    void deleteFactura(int id);
}
