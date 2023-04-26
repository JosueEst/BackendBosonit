package com.formacion.bosonit.examen_JPA_cascada.controller;

import com.formacion.bosonit.examen_JPA_cascada.application.CabeceraFraService;
import com.formacion.bosonit.examen_JPA_cascada.application.CabeceraFraServiceImpl;
import com.formacion.bosonit.examen_JPA_cascada.application.ClienteServiceImpl;
import com.formacion.bosonit.examen_JPA_cascada.application.LineasFraServiceImpl;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class Controller {
    @Autowired
    CabeceraFraServiceImpl cabeceraFraService;
    @Autowired
    ClienteServiceImpl clienteService;
    @Autowired
    LineasFraServiceImpl lineasFraService;

    @PostMapping("/cliente/insertClient")
    public ResponseEntity<ClienteOutputDto> insertarCliente (@RequestBody ClienteInputDto clienteInputDto){

        return ResponseEntity.ok().body(clienteService.insertarCliente(clienteInputDto));
    }
    @GetMapping ("cliente/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById (@PathVariable int id){
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @GetMapping ("/factura")
    public Iterable<CabeceraFraOutputDto> getAllBills (){

        return cabeceraFraService.getAllBills();
    }
    @PostMapping ("/factura/add")
    public ResponseEntity<CabeceraFraOutputDto> addCabeceraFra (@RequestBody CabeceraFraInputDto cabeceraFraInputDto){
        return ResponseEntity.ok().body(cabeceraFraService.addCabeceraFra(cabeceraFraInputDto));

    }
    @DeleteMapping ("/factura/{idFra}")
    public ResponseEntity<String> deleteFactura (@PathVariable int idFra) throws EntityNotFoundException{
        try {
            cabeceraFraService.deleteFactura(idFra);
            return ResponseEntity.ok().body("Factura con id: '"+idFra+"' eliminada");

        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/factura/linea/{idFra}")
    public ResponseEntity<LineasFraOutputDto> addLinea
            (@PathVariable int idFra, @RequestBody LineasFraInputDto lineasFraInputDto) throws EntityNotFoundException{
        try{
            return ResponseEntity.ok().body(lineasFraService.addLinea(idFra, lineasFraInputDto));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
