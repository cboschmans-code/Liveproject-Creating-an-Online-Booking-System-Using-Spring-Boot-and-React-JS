package com.manning.salonapi.controllers;

import com.manning.salonapi.entities.Ticket;
import com.manning.salonapi.exceptions.TicketNotFoundException;
import com.manning.salonapi.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/tickets", produces = "application/json")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "/{ticketId}")
    @Operation(summary = "VerifyTicketAPI")
    public Ticket validateTicket(@PathVariable Long ticketId) {
        try {
            return ticketService.validateTicket(ticketId);
        } catch (TicketNotFoundException tnfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ticket not found", tnfe);
        }
    }
}
