package com.bosonit.formacion.block1601appclienteviaje.passenger.application;

import com.bosonit.formacion.block1601appclienteviaje.exception.EntityNotFoundException;
import com.bosonit.formacion.block1601appclienteviaje.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerInputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.controller.dto.PassengerOutputDto;
import com.bosonit.formacion.block1601appclienteviaje.passenger.domain.Passenger;
import com.bosonit.formacion.block1601appclienteviaje.passenger.repository.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;
    public static final String PASSENGER_DELETED = "Viajero eliminado. ID: ";
    public static final String NO_CLIENTS = "No existen clientes";


    @Override
    public PassengerOutputDto addPassenger(PassengerInputDto passengerInputDto) throws UnprocessableEntityException {
        try {
            Passenger passenger = new Passenger(validation(passengerInputDto));
            return new PassengerOutputDto(passengerRepository.save(passenger));
        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    // TODO: en realidad salta un 'NoSuchValuePresent
    @Override
    public PassengerOutputDto getPassengerById(int idPassenger) {

            Passenger passenger = passengerRepository.findById(idPassenger)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente con id '" +idPassenger+ "' no encontrado"));
            return new PassengerOutputDto(passenger);
    }

    @Override
    public PassengerOutputDto getPassengerByName(String name)  {

        return new PassengerOutputDto(passengerRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con nombre '" +name+ "' no encontrado")));
    }

    @Override
    public List<PassengerOutputDto> getAllPassengers() throws EntityNotFoundException {

        try {
            List<Passenger> list = passengerRepository.findAll();
            return list.stream().map(PassengerOutputDto::new).toList();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(NO_CLIENTS);
        }
    }

    @Override
    public PassengerOutputDto updatePassenger(int idPassenger, PassengerInputDto passengerInputDto) throws UnprocessableEntityException, EntityNotFoundException {
        try {
            validation(passengerInputDto);
            Passenger passenger = passengerRepository.findById(idPassenger)
                    .orElseThrow(() -> new EntityNotFoundException ("Cliente con id '" +idPassenger+ "' no encontrado"));
            passenger.setName(passengerInputDto.getName());
            passenger.setSurname(passengerInputDto.getSurname());
            passenger.setAge(passengerInputDto.getAge());
            passenger.setEmail(passengerInputDto.getEmail());
            passenger.setPhoneNumber(passengerInputDto.getPhoneNumber());
            return new PassengerOutputDto(passengerRepository.save(passenger));

        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public String deletePassengerById(int idPassenger) throws EntityNotFoundException {

        passengerRepository.findById(idPassenger)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con id '" +idPassenger+ "' no encontrado"));
        passengerRepository.deleteById(idPassenger);
        return PASSENGER_DELETED + idPassenger;
    }

    @Override
    public PassengerInputDto validation(PassengerInputDto personInputDto) throws UnprocessableEntityException {
        if (personInputDto.getName() == null || personInputDto.getName().isBlank()) {
            throw new UnprocessableEntityException("Debe rellenar el campo 'name'.");
        } else if (personInputDto.getSurname() == null || personInputDto.getSurname().isBlank()) {
            throw new UnprocessableEntityException("Debe rellenar el campo 'surname'.");
        } else if (personInputDto.getAge() < 16 || personInputDto.getAge().toString().length() == 0) {
            throw new UnprocessableEntityException("Debe poner una edad. Los/las menores de 16 no pueden viajar solos/as");
        } else if (personInputDto.getEmail() == null || personInputDto.getEmail().isBlank()) {
            throw new UnprocessableEntityException("Debe poner un 'email'");
        } else if (personInputDto.getPhoneNumber() < 9 || personInputDto.getPhoneNumber().toString().length() == 0) {
            throw new UnprocessableEntityException("Debe poner un nº de teléfono con una longitud mínima de 9 caracteres");
        } else log.info("Datos validados correctamente !");
        return personInputDto;
    }
}
