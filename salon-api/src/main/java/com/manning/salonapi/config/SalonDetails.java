package com.manning.salonapi.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalonDetails {
    @Id
    Long id;
    @Value("${salon.name}")
    String name;
    @Value("${salon.address}")
    String address;
    @Value("${salon.city}")
    String city;
    @Value("${salon.state}")
    String state;
    @Value("${salon.zipcode}")
    String zipCode;
    @Value("${salon.phone}")
    String phone;
    @Value("${stripe.api.key}")
    private String apiKey;
}
