package com.bosonit.formacion.block1601appclienteviaje.passenger.controller;


import com.bosonit.formacion.block1601appclienteviaje.passenger.application.PassengerService;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerOutputDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/passenger")
public class PassengerController {


    @Autowired
    PassengerService passengerService;

    @PostMapping
    public ResponseEntity <PassengerOutputDto> addPassenger (@RequestBody PassengerInputDto passengerInputDto){

            return ResponseEntity.status(HttpStatus.CREATED).body(passengerService.addPassenger(passengerInputDto));
    }

    @GetMapping ("/id/{idPassenger}")
    public ResponseEntity <PassengerOutputDto> getPassengerById (@PathVariable int idPassenger) throws EntityNotFoundException {

            return ResponseEntity.status(HttpStatus.OK).body(passengerService.getPassengerById(idPassenger));
    }
    @GetMapping ("/name/{name}")
    public ResponseEntity <PassengerOutputDto> getPassengerByName (@PathVariable String name) throws EntityNotFoundException{

            return ResponseEntity.status(HttpStatus.FOUND).body(passengerService.getPassengerByName(name));
    }
    @GetMapping
    public ResponseEntity<List<PassengerOutputDto>> getAllPassengers() throws EntityNotFoundException {

            return new ResponseEntity<>(passengerService.getAllPassengers().stream().toList(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity <PassengerOutputDto> updatePassenger
            (@RequestParam int idPassenger, @RequestBody PassengerInputDto passengerInputDto){

            return ResponseEntity.status(HttpStatus.OK).body(passengerService.updatePassenger(idPassenger, passengerInputDto));
    }

    @DeleteMapping ("/{idPassenger}")
    public String deletePassengerById (@PathVariable int idPassenger){

            return passengerService.deletePassengerById(idPassenger);
    }
}
