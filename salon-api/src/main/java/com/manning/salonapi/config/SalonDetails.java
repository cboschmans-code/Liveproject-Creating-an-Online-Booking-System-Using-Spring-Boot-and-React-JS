package com.manning.salonapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;

@Entity
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
}
