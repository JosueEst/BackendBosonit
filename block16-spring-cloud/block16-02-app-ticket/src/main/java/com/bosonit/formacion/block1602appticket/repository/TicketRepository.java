package com.bosonit.formacion.block1602appticket.repository;

import com.bosonit.formacion.block1602appticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
