package com.bosonit.formacion.block1602appticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class Ticket {
    @Id
    @GeneratedValue
    Integer idTicket;

    Integer passengerId;

    String passengerName;

    String passengerSurname;

    String passengerEmail;

    String tripOrigin;

    String tripDestination;

    String tripDepartureDate;

    String tripArrivalDate;
}
