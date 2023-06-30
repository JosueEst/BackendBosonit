package com.bosonit.formacion.block1601appclienteviaje.start;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.passenger.repository.PassengerRepository;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import com.bosonit.formacion.block1601appclienteviaje.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartComponent implements CommandLineRunner {

    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    TripRepository tripRepository;
    public static final String PASSENGER = "Passenger";
    public static final Integer AGE = 16;
    public static final Long PHONE_NUMBER = 666555444L;
    public static final String ORIGIN = "Origin";
    public static final String DESTINATION = "Destination";

    @Override
    public void run(String... args) throws Exception {

        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Passenger passenger = new Passenger();
            passenger.setName(PASSENGER + i);
            passenger.setSurname(PASSENGER + i);
            passenger.setAge(AGE + i);
            passenger.setEmail(i + "@gmail.com");
            passenger.setPhoneNumber(PHONE_NUMBER);
            passengerRepository.save(passenger);
            passengerList.add(passenger);
        }


        Trip trip = new Trip();
        trip.setOrigin(ORIGIN + 1);
        trip.setDestination(DESTINATION + 2);
        trip.setDepartureDate("2023-10-10 20:00:00");
        trip.setArrivalDate("2023-10-25 07:30:00");
        trip.getPassengerList().addAll(passengerList.subList(0, 2));

        Trip trip2 = new Trip();
        trip.setOrigin(ORIGIN + 2);
        trip.setDestination(DESTINATION + 2);
        trip.setDepartureDate("2023-10-10 20:00:00");
        trip.setArrivalDate("2023-10-25 07:30:00");
        trip.getPassengerList().addAll(passengerList.subList(3, 4));

    }
}
