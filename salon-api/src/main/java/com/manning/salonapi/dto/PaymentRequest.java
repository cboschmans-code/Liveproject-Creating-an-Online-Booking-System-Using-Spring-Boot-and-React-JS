package com.manning.salonapi.dto;

public record PaymentRequest(Long slotId, Long salonServiceDetailId, String firstName, String lastName,
                             String email, String phoneNumber) {
}
