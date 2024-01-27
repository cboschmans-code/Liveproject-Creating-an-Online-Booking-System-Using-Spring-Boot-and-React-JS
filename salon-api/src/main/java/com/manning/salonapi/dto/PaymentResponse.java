package com.manning.salonapi.dto;

import com.manning.salonapi.config.SalonDetails;
import com.manning.salonapi.entities.Ticket;

public record PaymentResponse(Ticket ticket, SalonDetails salonDetails) {
}
