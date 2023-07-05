package com.bosonit.formacion.block1601appclienteviaje.trip.controller;

import com.bosonit.formacion.block1601appclienteviaje.trip.application.TripService;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripInputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping
    public ResponseEntity<TripOutputDto> addTrip ( @RequestBody TripInputDto tripInputDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.addTrip(tripInputDto));
    }
    //Method to add a passenger to a trip
    @PostMapping ("/addPassenger")
    public ResponseEntity <String> addPassengerToTrip (@RequestParam int idTrip, @RequestParam int idPassenger){
        return ResponseEntity.ok(tripService.addPassengerToTrip(idTrip, idPassenger));
    }
    @GetMapping ("/{idTrip}")
    public ResponseEntity<TripOutputDto> getTripById (@PathVariable int idTrip){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripById(idTrip));
    }
    @GetMapping
    public ResponseEntity<List<TripOutputDto>> getAllTrips (){
        return new ResponseEntity<>(tripService.getAllTrips().stream().toList(), HttpStatus.OK);
    }
    //Method to obtain de number of passengers of a trip
    @GetMapping ("passenger/count/{idTrip}")
    public ResponseEntity <Integer> countPassengers (@PathVariable int idTrip){
        return ResponseEntity.ok(tripService.countPassengers(idTrip));
    }
    //Method to show the status of a trip
    @GetMapping ("status/show/{idTrip}")
    public ResponseEntity<String> showStatus (@PathVariable int idTrip){
        return ResponseEntity.ok(tripService.showStatus(idTrip));
    }
    @PutMapping
    public ResponseEntity <TripOutputDto> updateTrip (@RequestParam int idTrip,@RequestBody TripInputDto tripInputDto){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.updateTrip(idTrip, tripInputDto));
    }
    //Method to update the status of a trip
    @PutMapping ("/status/{idTrip}/{status}")
    public ResponseEntity <String> changeStatus (@PathVariable int idTrip, @PathVariable String status){
        return ResponseEntity.ok(tripService.changeStatus(idTrip, status));
    }
    @DeleteMapping ("/idTrip")
    public ResponseEntity<String> deleteTripById (@PathVariable int idTrip){
        return ResponseEntity.ok(tripService.deleteTripById(idTrip)) ;
    }
}
