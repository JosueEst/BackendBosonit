package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraOutputDto;

public interface LineasFraService {


    LineasFraOutputDto addLinea(int id, LineasFraInputDto lineasFraInputDto);
}
