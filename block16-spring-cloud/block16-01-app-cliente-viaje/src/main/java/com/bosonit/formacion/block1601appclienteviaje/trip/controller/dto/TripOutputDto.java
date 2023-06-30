package com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TripOutputDto {

    int idTrip;
    String origin;

    String destination;

    String departureDate;

    String arrivalDate;
    List<Passenger> passengerList = new ArrayList<>();

    public TripOutputDto (Trip trip){
        this.idTrip=trip.getIdTrip();
        this.origin=trip.getOrigin();
        this.destination=trip.getDestination();
        this.departureDate=trip.getDepartureDate();
        this.arrivalDate=trip.getArrivalDate();
        this.passengerList=trip.getPassengerList().stream().toList();
    }
}
