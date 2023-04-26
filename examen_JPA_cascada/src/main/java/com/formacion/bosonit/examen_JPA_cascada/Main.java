package com.formacion.bosonit.examen_JPA_cascada;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.*;
import com.formacion.bosonit.examen_JPA_cascada.domain.CabeceraFra;
import com.formacion.bosonit.examen_JPA_cascada.domain.Cliente;
import com.formacion.bosonit.examen_JPA_cascada.domain.LineasFra;
import com.formacion.bosonit.examen_JPA_cascada.repository.CabeceraFraRepository;
import com.formacion.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import com.formacion.bosonit.examen_JPA_cascada.repository.LineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    CabeceraFraRepository cabeceraFraRepository;
    @Autowired
    LineasFraRepository lineasFraRepository;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ClienteInputDto clienteInputDto = new ClienteInputDto("Josué", new ArrayList<>());
        Cliente cliente = new Cliente(clienteInputDto);
        clienteRepository.save(cliente);


        CabeceraFraInputDto cabeceraFraInputDto = new CabeceraFraInputDto(0.0,cliente,new ArrayList<>());
        CabeceraFra cabeceraFra = new CabeceraFra(cabeceraFraInputDto);
        cabeceraFraRepository.save(cabeceraFra);
        LineasFraInputDto lineasFraInputDto1 = new LineasFraInputDto("P1", 10.0, 5.0, 1);
        LineasFraInputDto lineasFraInputDto2 = new LineasFraInputDto("P2", 5.0, 2.0, 1);

        LineasFra lineasFra1 = new LineasFra(lineasFraInputDto1);
        lineasFra1.setIdFra(cabeceraFra);
        LineasFra lineasFra2 = new LineasFra(lineasFraInputDto2);
        lineasFra2.setIdFra(cabeceraFra);
        List<LineasFra> list = new ArrayList<>();
        list.add(lineasFra1);
        list.add(lineasFra2);
        cabeceraFra.setLineasFraList(list);

        cabeceraFraRepository.save(cabeceraFra);



    }
}
