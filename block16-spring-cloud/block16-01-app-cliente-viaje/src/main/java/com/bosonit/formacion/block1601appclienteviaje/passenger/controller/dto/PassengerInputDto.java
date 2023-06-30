package com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PassengerInputDto {

    String name;

    String surname;

    Integer age;

    String email;

    Long phoneNumber;
    List<Trip> tripList = new ArrayList<>();


}
