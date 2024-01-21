package com.manning.salonapi.controllers;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.services.SalonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class SalonServiceController {

    SalonService salonService;

    public SalonServiceController(SalonService salonservice) {
        this.salonService = salonservice;
    }

    @GetMapping("/retrieveAvailableSalonServices")
    @Operation(summary = "RetrieveAvailableSalonServicesAPI")
    public List<SalonServiceDetail> retrieveAvailableSalonServicesAPI() {

        return salonService.findAll();
    }
}
