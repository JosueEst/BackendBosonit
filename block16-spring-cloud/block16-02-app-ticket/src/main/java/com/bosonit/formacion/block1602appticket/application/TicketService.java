package com.bosonit.formacion.block1602appticket.application;

import com.bosonit.formacion.block1602appticket.domain.Ticket;
import com.bosonit.formacion.block1602appticket.domain.Trip;

public interface TicketService {

    Ticket generateTicket (int PassengerId, int idTrip);
    Ticket getTicket (int idTicket);
}
