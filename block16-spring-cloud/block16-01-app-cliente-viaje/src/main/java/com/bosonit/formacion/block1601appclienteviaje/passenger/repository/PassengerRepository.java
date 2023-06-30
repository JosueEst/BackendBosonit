package com.bosonit.formacion.block1601appclienteviaje.passenger.repository;

import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository <Passenger, Integer> {
    Optional<Passenger> findByName(String name);;
}
