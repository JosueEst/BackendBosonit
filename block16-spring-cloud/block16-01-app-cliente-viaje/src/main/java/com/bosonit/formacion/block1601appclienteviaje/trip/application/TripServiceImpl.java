package com.bosonit.formacion.block1601appclienteviaje.trip.application;

import com.bosonit.formacion.block1601appclienteviaje.exception.EntityNotFoundException;
import com.bosonit.formacion.block1601appclienteviaje.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.passenger.repository.PassengerRepository;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripInputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.controller.dto.TripOutputDto;
import com.bosonit.formacion.block1601appclienteviaje.trip.domain.Trip;
import com.bosonit.formacion.block1601appclienteviaje.trip.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService{

    private static final String TRIP_DELETED = "Viaje eliminado. ID: ";
    @Autowired
    TripRepository tripRepository;
    @Autowired
    PassengerRepository passengerRepository;

    public static final String TRIP_NOT_FOUND = "Viaje no encontrado. ID: ";
    public static final String NO_TRIPS = "No existen viajes";

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) throws UnprocessableEntityException {
        try{
            validation(tripInputDto);
            Trip trip = new Trip(tripInputDto);
            return new TripOutputDto (tripRepository.save(trip));
        }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public TripOutputDto getTripById(int idTrip) throws EntityNotFoundException {
        try{
            Trip trip=tripRepository.findById(idTrip).orElseThrow();
            return new TripOutputDto (trip);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(TRIP_NOT_FOUND+idTrip);
        }
    }

    @Override
    public List<TripOutputDto> getAllTrips() throws EntityNotFoundException {
        try {
            List<Trip> tripList = tripRepository.findAll();
            return tripList.stream().map(TripOutputDto::new).toList();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(NO_TRIPS);
        }
    }
    @Override
    public TripOutputDto updateTrip(int idTrip, TripInputDto tripInputDto)
            throws EntityNotFoundException, UnprocessableEntityException{
        try{
            validation(tripInputDto);
            Trip trip = tripRepository.findById(idTrip).orElseThrow();
            trip.setOrigin(tripInputDto.getOrigin());
            trip.setDestination(tripInputDto.getDestination());
            trip.setDepartureDate(tripInputDto.getDepartureDate());
            trip.setArrivalDate(tripInputDto.getArrivalDate());

           return new TripOutputDto(tripRepository.save(trip)) ;
        }catch (EntityNotFoundException e ){
            throw new EntityNotFoundException(TRIP_NOT_FOUND+idTrip);
        }catch (UnprocessableEntityException exception){
            throw new UnprocessableEntityException(exception.getMessage());
        }
    }
    @Override
    public String deleteTripById(int idTrip) throws EntityNotFoundException{
        try{
            tripRepository.findById(idTrip).orElseThrow();
            tripRepository.deleteById(idTrip);
            return TRIP_DELETED+idTrip;
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(TRIP_NOT_FOUND+idTrip);
        }
    }

    @Override
    public String addPassengerToTrip(int idTrip, int idPassenger) throws EntityNotFoundException {
        try{
            Trip trip = tripRepository.findById(idTrip).orElseThrow();
            //validar si el viaje ya tiene 40 pasajeros
            if(trip.getPassengerList().size()<40){

                Passenger passenger = passengerRepository.findById(idPassenger).orElseThrow();

                if(trip.getPassengerList().contains(passenger)){
                    return "El cliente con id "+idPassenger+" ya ha reservado este viaje con id "+idTrip;
                }else{
                    trip.getPassengerList().add(passenger);
                    return "El cliente con id "+idPassenger+" ha hecho una reserva para el viaje con id "+idTrip;
                }
            }else{
                return "No es posible reservar. El aforo para este viaje ya está completo";
            }
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(TRIP_NOT_FOUND+idTrip);
        }
    }

    //Method for obtaining the number of passengers on a given trip
    @Override
    public Integer countPassengers(int idTrip) throws EntityNotFoundException{
        try{
            Trip trip = tripRepository.findById(idTrip).orElseThrow();
            return trip.getPassengerList().size();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(TRIP_NOT_FOUND+idTrip);
        }
    }

    @Override
    public String changeStatus(int idTrip, String status) {

            Trip trip = tripRepository.findById(idTrip)
                    .orElseThrow(() -> new EntityNotFoundException(TRIP_NOT_FOUND + idTrip));
            trip.setStatus(status);
            tripRepository.save(trip);
            return "El nuevo status del viaje "+idTrip+" es: "+status;

    }

    @Override
    public String showStatus(int idTrip) {
        Trip trip = tripRepository.findById(idTrip).orElseThrow(() -> new EntityNotFoundException(TRIP_NOT_FOUND+idTrip));
        return trip.getStatus();
    }

    @Override
    public TripInputDto validation(TripInputDto tripInputDto) throws UnprocessableEntityException {
        if(tripInputDto.getOrigin().isBlank() || tripInputDto.getOrigin() == null){
            throw new UnprocessableEntityException("Debe rellenar el campo 'origin'.");
        }else if(tripInputDto.getDestination().isBlank() || tripInputDto.getDestination() ==null){
            throw new UnprocessableEntityException("Debe rellenar el campo 'destination'.");
        }else if(tripInputDto.getDepartureDate().isBlank() || tripInputDto.getDepartureDate() == null){
            throw new UnprocessableEntityException("Debe rellenar el campo 'departureDate'");
        }else if (tripInputDto.getArrivalDate().isBlank() || tripInputDto.getArrivalDate() == null){
            throw new UnprocessableEntityException("Debe rellenar el campo 'arrivalDate");

        }else log.info("Datos validados correctamente !");
        return tripInputDto;
    }
}
