package com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TripInputDto {

    String origin;

    String destination;

    String departureDate;

    String arrivalDate;


}
