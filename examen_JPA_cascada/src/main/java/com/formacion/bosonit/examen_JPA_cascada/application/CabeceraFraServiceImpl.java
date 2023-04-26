package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.CabeceraFra;
import com.formacion.bosonit.examen_JPA_cascada.repository.CabeceraFraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabeceraFraServiceImpl implements CabeceraFraService{
    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    //Método para añadir una factura
    @Override
    public CabeceraFraOutputDto addCabeceraFra(CabeceraFraInputDto cabeceraFraInputDto) {
        return cabeceraFraRepository.save(new CabeceraFra(cabeceraFraInputDto)).cabeceraFraToCabeceraFraOutputDto();
    }

    //Método para obtener todas las facturas
    @Override
    public Iterable<CabeceraFraOutputDto> getAllBills() {
        return cabeceraFraRepository.findAll().stream().map(CabeceraFra::cabeceraFraToCabeceraFraOutputDto).toList();
    }
    @Override
    public void deleteFactura(int id){
        cabeceraFraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        cabeceraFraRepository.deleteById(id);
    }
}
