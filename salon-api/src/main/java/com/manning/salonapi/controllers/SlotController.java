package com.manning.salonapi.controllers;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import com.manning.salonapi.services.SalonServiceDetailService;
import com.manning.salonapi.services.SlotService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping(path = "api/slots", produces = "application/json")
public class SlotController {

private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }


    @GetMapping(path = "retrieveAvailableSlots/{salonServiceId}/{formattedDate}")
    @Operation(summary = "RetrieveAvailableSlotsAPI")
    @Transactional(readOnly = true)
    public List<Slot> allAvailableSlotsForAServiceOnADay(@PathVariable("salonServiceId") Long salonServiceId, @PathVariable("formattedDate") String formattedDate) {
        return slotService.findAllAvaibleSlotsForAServiceOnADay(salonServiceId, formattedDate);
    }


}
