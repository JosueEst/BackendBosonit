package com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PassengerOutputDto {
    int idPassenger;

    String name;

    String surname;

    Integer age;

    String email;

    Long phoneNumber;
    // List<Trip> tripList = new ArrayList<>();

    public PassengerOutputDto (Passenger passenger){
        this.idPassenger=passenger.getIdPassenger();
        this.name =passenger.getName();
        this.surname = passenger.getSurname();
        this.age = passenger.getAge();
        this.email = passenger.getEmail();
        this.phoneNumber = passenger.getPhoneNumber();
        // this.tripList = passenger.getTripList();
    }
}
