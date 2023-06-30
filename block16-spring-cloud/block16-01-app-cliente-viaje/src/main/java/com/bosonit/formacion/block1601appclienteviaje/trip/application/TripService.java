package com.bosonit.formacion.block1601appclienteviaje.trip.application;

import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripInputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripOutputDto;

import java.util.List;

public interface TripService {

    TripOutputDto addTrip (TripInputDto tripInputDto);
    TripOutputDto getTripById (int idTrip);
    List<TripOutputDto> getAllTrips();
    TripOutputDto updateTrip (int idTrip, TripInputDto tripInputDto);
    String deleteTripById (int idTrip);

    String addPassengerToTrip (int idTrip, int idPassenger);
    Integer countPassengers (int idTrip);
    String changeStatus (int idTrip, String status);
    String showStatus (int idTrip);
    TripInputDto validation(TripInputDto tripInputDto);
}
