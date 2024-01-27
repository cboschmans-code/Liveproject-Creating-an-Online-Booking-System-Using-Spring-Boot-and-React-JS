package com.manning.salonapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    TicketStatus ticketStatus;

    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
}
