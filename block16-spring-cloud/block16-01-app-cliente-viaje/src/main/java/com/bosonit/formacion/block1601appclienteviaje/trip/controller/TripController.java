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
    @GetMapping ("/{idTrip}")
    public ResponseEntity<TripOutputDto> getTripById (@PathVariable int idTrip){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripById(idTrip));
    }
    @GetMapping
    public ResponseEntity<List<TripOutputDto>> getAllTrips (){
        return new ResponseEntity<>(tripService.getAllTrips().stream().toList(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity <TripOutputDto> updateTrip (@RequestParam int idTrip,@RequestBody TripInputDto tripInputDto){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.updateTrip(idTrip, tripInputDto));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteTripById (int idTrip){
        return ResponseEntity.ok(tripService.deleteTripById(idTrip)) ;
    }
    //Method to add a passenger to a trip
    @PostMapping ("/addPassanger")
    public ResponseEntity <String> addPassengerToTrip (@RequestParam int idTrip, @RequestParam int idPassenger){
        return ResponseEntity.ok(tripService.addPassengerToTrip(idTrip, idPassenger));
    }
    //Method to obtain de number of passengers of a trip
    @GetMapping ("passenger/count")
    public ResponseEntity <Integer> countPassengers (@RequestParam int idTrip){
        return ResponseEntity.ok(tripService.countPassengers(idTrip));
    }
    //Method to update the status of a trip
    @PutMapping ("/status")
    public ResponseEntity <String> changeStatus (@RequestParam int idTrip, @RequestParam String status){
        return ResponseEntity.ok(tripService.changeStatus(idTrip, status));
    }
    //Method to show the status of a trip
    @GetMapping ("status/show/{idTrip}")
    public ResponseEntity<String> showStatus (@PathVariable int idTrip){
        return ResponseEntity.ok(tripService.showStatus(idTrip));
    }

}
