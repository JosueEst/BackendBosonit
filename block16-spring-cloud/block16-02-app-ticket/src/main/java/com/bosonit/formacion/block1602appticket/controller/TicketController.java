package com.bosonit.formacion.block1602appticket.controller;

import com.bosonit.formacion.block1602appticket.application.TicketService;
import com.bosonit.formacion.block1602appticket.domain.Ticket;
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
    public ResponseEntity<Ticket> generateTicket (@PathVariable int PassengerId, @PathVariable int idTrip){

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.generateTicket(PassengerId, idTrip));
    }

    @GetMapping("/{idTicket}")
    public ResponseEntity<Ticket> getTicket (@PathVariable int idTicket){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.getTicket(idTicket));
    }
}
