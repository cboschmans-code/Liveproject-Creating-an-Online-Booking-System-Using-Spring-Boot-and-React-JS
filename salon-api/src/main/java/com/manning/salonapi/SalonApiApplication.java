package com.manning.salonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.manning.salonapi.entities")
public class SalonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonApiApplication.class, args);
	}

}
