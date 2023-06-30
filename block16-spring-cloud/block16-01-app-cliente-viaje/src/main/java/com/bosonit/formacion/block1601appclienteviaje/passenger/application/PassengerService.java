package com.bosonit.formacion.block1601appclienteviaje.passenger.application;

import com.bosonit.formacion.block1601appclienteviaje.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerOutputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;

import java.util.List;

public interface PassengerService {
    PassengerOutputDto addPassenger (PassengerInputDto passengerInputDto);
    PassengerOutputDto getPassengerById (int idPassenger);
    PassengerOutputDto getPassengerByName (String name);
    List <PassengerOutputDto> getAllPassengers ();
    PassengerOutputDto updatePassenger (int idPassenger, PassengerInputDto passengerInputDto);
    String deletePassengerById (int idPassenger);
    PassengerInputDto validation (PassengerInputDto personInputDto) throws UnprocessableEntityException;

}
