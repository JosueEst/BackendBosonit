package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteOutputDto;

public interface ClienteService {
    ClienteOutputDto insertarCliente(ClienteInputDto clienteInputDto);

    ClienteOutputDto getClienteById(int id);
}
