package com.formacion.bosonit.examen_JPA_cascada.application;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraOutputDto;
import com.formacion.bosonit.examen_JPA_cascada.domain.CabeceraFra;
import com.formacion.bosonit.examen_JPA_cascada.domain.LineasFra;
import com.formacion.bosonit.examen_JPA_cascada.repository.CabeceraFraRepository;
import com.formacion.bosonit.examen_JPA_cascada.repository.LineasFraRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineasFraServiceImpl implements LineasFraService{
    @Autowired
    LineasFraRepository lineasFraRepository;
    @Autowired
    CabeceraFraRepository cabeceraFraRepository;
    @Override
    public LineasFraOutputDto addLinea(int id, LineasFraInputDto lineasFraInputDto) {
        CabeceraFra cabeceraFra= cabeceraFraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        lineasFraInputDto.setIdFra(id);
        LineasFra lineasFra=new LineasFra(lineasFraInputDto);
        lineasFra.setIdFra(cabeceraFra);//Setteamos puesto que aqui llega el id pero necesita el objeto de la factura

        return lineasFraRepository.save(lineasFra).lineasFraToLineasFraOutputDto();
    }
}
