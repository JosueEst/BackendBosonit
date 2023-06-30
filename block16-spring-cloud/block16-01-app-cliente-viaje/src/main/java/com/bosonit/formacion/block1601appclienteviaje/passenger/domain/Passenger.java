package com.bosonit.formacion.block1601appclienteviaje.passenger.domain;

import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Passenger {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "idPassenger")
    private int idPassenger;
    @Column (name = "name")
    private String name;
    @Column (name = "surname")
    private String surname;
    @Column (name = "age")
    private Integer age;
    @Column (name = "email")
    private String email;
    @Column (name = "phoneNumber")
    private Long phoneNumber;
    @ManyToMany (mappedBy = "passengerList")
    private List<Trip> tripList = new ArrayList<>();

    public Passenger (PassengerInputDto passengerInputDto){
        this.name = passengerInputDto.getName();
        this.surname = passengerInputDto.getSurname();
        this.age = passengerInputDto.getAge();
        this.email = passengerInputDto.getEmail();
        this.phoneNumber = passengerInputDto.getPhoneNumber();
        this.tripList = passengerInputDto.getTripList();
    }


}
