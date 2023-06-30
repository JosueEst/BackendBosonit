package com.bosonit.formacion.block1602appticket.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Trip {

    int idTrip;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    List<Passenger> passengerList = new ArrayList<>();
    String status = "Disponible";

}
