package com.bosonit.formacion.block1602appticket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Passenger {

    int idPassenger;
    String name;
    String surname;
    Integer age;
    String email;
    Long phoneNumber;
    private List<Trip> tripList = new ArrayList<>();
}
