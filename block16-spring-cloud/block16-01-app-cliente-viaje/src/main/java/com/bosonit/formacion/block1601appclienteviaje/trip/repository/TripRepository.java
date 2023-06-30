package com.bosonit.formacion.block1601appclienteviaje.trip.repository;

import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Integer> {

}
