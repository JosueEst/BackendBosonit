package com.bosonit.formacion.block1601appclienteviaje.passenger.application;

import com.bosonit.formacion.block1601appclienteviaje.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerOutputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.passenger.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    PassengerRepository passengerRepository;
    public static final String PASSENGER_DELETED = "Viajero eliminado. ID: ";
    public static final String NO_CLIENTS = "No existen clientes";

    @Override
    public PassengerOutputDto addPassenger(PassengerInputDto passengerInputDto) throws UnprocessableEntityException {
        try{
            Passenger passenger = new Passenger(validation(passengerInputDto));
            return new PassengerOutputDto(passengerRepository.save(passenger));
        }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public PassengerOutputDto getPassengerById(int idPassenger) {
        try{
            return new PassengerOutputDto (passengerRepository.findById(idPassenger)
                    .orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
    @Override
    public PassengerOutputDto getPassengerByName(String name) {
        try{
            return new PassengerOutputDto (passengerRepository.findByName(name)
                    .orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PassengerOutputDto> getAllPassengers() throws EntityNotFoundException{
        try{
            List <Passenger> list = passengerRepository.findAll();
            return list.stream().map(PassengerOutputDto::new).toList();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(NO_CLIENTS);
        }
    }

    @Override
    public PassengerOutputDto updatePassenger(int idPassenger, PassengerInputDto passengerInputDto) throws UnprocessableEntityException, EntityNotFoundException {
        try{
            validation(passengerInputDto);
            Passenger passenger=passengerRepository.findById(idPassenger).orElseThrow(EntityNotFoundException::new);
            passenger.setName(passengerInputDto.getName());
            passenger.setSurname(passengerInputDto.getSurname());
            passenger.setAge(passengerInputDto.getAge());
            passenger.setEmail(passengerInputDto.getEmail());
            passenger.setPhoneNumber(passengerInputDto.getPhoneNumber());

            return new PassengerOutputDto(passengerRepository.save(passenger));
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Cliente no encontrado");
        }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException(e.getMessage());
        }
    }
    @Override
    public String deletePassengerById(int idPassenger) throws EntityNotFoundException{
        try {
            passengerRepository.findById(idPassenger).orElseThrow(EntityNotFoundException::new);
            passengerRepository.deleteById(idPassenger);
            return PASSENGER_DELETED+idPassenger;
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
    @Override
    public PassengerInputDto validation(PassengerInputDto personInputDto) throws UnprocessableEntityException {
        if(personInputDto.getName().isBlank()){
            throw new UnprocessableEntityException("Debe rellenar el campo 'name'.");
        }else if(personInputDto.getSurname().isBlank()){
            throw new UnprocessableEntityException("Debe rellenar el campo 'surname'.");
        }else if(personInputDto.getAge() == null ){
            throw new UnprocessableEntityException("Debe rellenar el campo 'age'");
        }else if (personInputDto.getAge() < 16){
            throw new UnprocessableEntityException("Los/las menores de 16 no pueden viajar solos/as");
        }else if(personInputDto.getEmail() == null){
            throw new UnprocessableEntityException("Debe rellenar el campo 'email'");
        }else if (personInputDto.getPhoneNumber() == null ){
            throw new UnprocessableEntityException("Debe rellenar el campo 'phoneNumber'");
        }else if (personInputDto.getPhoneNumber() < 9){
            throw new UnprocessableEntityException("El nº de teléfono deber tener un longitud mínima de 9 caracteres");
        }else if(personInputDto.getTripList() == null){
            throw new UnprocessableEntityException("El campo 'tripList' no puede ser nulo");
        }else log.info("Datos validados correctamente !");
        return personInputDto;
    }
}
