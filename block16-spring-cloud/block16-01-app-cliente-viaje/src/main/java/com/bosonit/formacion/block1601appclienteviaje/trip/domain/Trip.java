package com.bosonit.formacion.block1601appclienteviaje.trip.domain;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripInputDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Trip {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int idTrip;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column
    private String departureDate;
    @Column
    private String arrivalDate;
    @ManyToMany //(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengerList = new ArrayList<>();
    @Column
    private String status = "Disponible";

    public Trip (TripInputDto tripInputDto){
        this.origin=tripInputDto.getOrigin();
        this.destination= tripInputDto.getDestination();
        this.departureDate = tripInputDto.getDepartureDate();
        this.arrivalDate=tripInputDto.getArrivalDate();
    }
}
