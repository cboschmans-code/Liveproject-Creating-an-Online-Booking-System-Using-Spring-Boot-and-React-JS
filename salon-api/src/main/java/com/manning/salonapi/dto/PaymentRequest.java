package com.manning.salonapi.dto;

public record PaymentRequest(Long slotID, Long salonServiceDetailID, String firstName, String lastName,
                             String email, String phoneNumber) {
}
