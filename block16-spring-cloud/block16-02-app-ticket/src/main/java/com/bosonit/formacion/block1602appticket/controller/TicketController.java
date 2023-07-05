package com.bosonit.formacion.block1602appticket.controller;

import com.bosonit.formacion.block1602appticket.application.TicketService;
import com.bosonit.formacion.block1602appticket.domain.Ticket;
import com.bosonit.formacion.block1602appticket.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/{PassengerId}/{idTrip}")
    public ResponseEntity generateTicket (@PathVariable int PassengerId, @PathVariable int idTrip) throws EntityNotFoundException {

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.generateTicket(PassengerId, idTrip));
        }catch (EntityNotFoundException e){
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntityNotFoundException(e.getMessage()) );
        }

    }

    @GetMapping("/{idTicket}")
    public ResponseEntity getTicket (@PathVariable int idTicket){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.getTicket(idTicket));
        }catch (EntityNotFoundException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntityNotFoundException(e.getMessage()) );
        }
    }
}
