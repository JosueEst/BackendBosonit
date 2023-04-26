package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public ClienteOutputDto insertarCliente(ClienteInputDto clienteInputDto) {
        return clienteRepository.save(new Cliente(clienteInputDto)).clienteToClienteOutpuDto();
    }
    @Override
    public ClienteOutputDto getClienteById (int id){
        return clienteRepository.findById(id).orElseThrow().clienteToClienteOutpuDto();
    }
}
