package com.manning.salonapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "selected_service_id")
    SalonServiceDetail selectedService;

    @OneToOne
    @JoinColumn(name = "slot_id")
    Slot slot;

    Long amount;

    PaymentStatus status;

    @CreatedDate
    LocalDateTime created;

    @LastModifiedDate
    LocalDateTime updated;

    String intentId;

    String clientSecret;

    String firstName;

    String lastName;

    String email;

    String phoneNumber;


}
