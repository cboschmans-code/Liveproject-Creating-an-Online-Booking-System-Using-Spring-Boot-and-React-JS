package com.manning.salonapi.controllers;

import com.manning.salonapi.config.SalonDetails;
import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import com.manning.salonapi.services.SalonServiceDetailService;
import com.manning.salonapi.tepositories.SalonServiceDetailRepository;
import com.manning.salonapi.tepositories.SlotRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.antlr.v4.runtime.tree.pattern.ParseTreePatternMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


@RestController
@RequestMapping(path = "api/services", produces = "application/json")
public class SalonServiceDetailController {

private final SalonServiceDetailService salonServiceDetailService;

    public SalonServiceDetailController(SalonServiceDetailService salonServiceDetailService) {
        this.salonServiceDetailService = salonServiceDetailService;
    }

    @GetMapping(path = "retrieveAvailableSalonServices")
    @Operation(summary = "RetrieveAvailableSalonServicesAPI")
    public List<SalonServiceDetail> allAvailableSalonServices() {
        return salonServiceDetailService.findAllServices();
    }




}
