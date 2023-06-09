package com.bosonit.formacion.block1602appticket.application;

import com.bosonit.formacion.block1602appticket.domain.Passenger;
import com.bosonit.formacion.block1602appticket.domain.Ticket;
import com.bosonit.formacion.block1602appticket.domain.Trip;
import com.bosonit.formacion.block1602appticket.exception.EntityNotFoundException;
import com.bosonit.formacion.block1602appticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TripFeign tripFeign;
    @Value("${restTemplate.url}")
    private String restTemplateUrl;

    @Override
    public Ticket generateTicket(int PassengerId,int idTrip ) throws EntityNotFoundException {

        try{
            Passenger passenger =
                    Optional.ofNullable(new RestTemplate().getForObject(restTemplateUrl+"id/"+PassengerId, Passenger.class, PassengerId))
                            .orElseThrow(() -> new EntityNotFoundException("Pasajero no encontrado"));

            Trip trip = tripFeign.getTripById(idTrip);

            Ticket ticket = new Ticket();
            ticket.setPassengerId(passenger.getIdPassenger());
            ticket.setPassengerName(passenger.getName());
            ticket.setPassengerSurname(passenger.getSurname());
            ticket.setPassengerEmail(passenger.getEmail());
            ticket.setTripOrigin(trip.getOrigin());
            ticket.setTripDestination(trip.getDestination());
            ticket.setTripDepartureDate(trip.getDepartureDate());
            ticket.setTripArrivalDate(trip.getArrivalDate());

            ticketRepository.save(ticket);

            return ticket;
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Viaje no encontrado. ID: "+ idTrip);
        }
    }

    @Override
    public Ticket getTicket(int idTicket) {
        return ticketRepository
                .findById(idTicket)
                .orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado. ID: "+ idTicket));
    }
}
