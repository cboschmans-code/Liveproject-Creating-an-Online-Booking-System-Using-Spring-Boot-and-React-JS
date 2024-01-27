package com.manning.salonapi.services;

import com.manning.salonapi.entities.Ticket;
import com.manning.salonapi.exceptions.TicketNotFoundException;
import com.manning.salonapi.tepositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket validateTicket(Long ticketId) throws TicketNotFoundException{
        return ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
    }
}
