package com.manning.salonapi.controllers;

import com.manning.salonapi.config.SalonDetails;
import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import com.manning.salonapi.tepositories.SalonServiceDetailRepository;
import com.manning.salonapi.tepositories.SlotRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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

    private final SalonServiceDetailRepository salonServiceDetailRepository;
    private final SlotRepository slotRepository;

    public SalonServiceDetailController(SalonServiceDetailRepository salonServiceDetailRepository, SlotRepository slotRepository) {
        this.salonServiceDetailRepository = salonServiceDetailRepository;
        this.slotRepository = slotRepository;
    }

    @Tag(name = "RetrieveAvailableSalonServicesAPI", description = "RetrieveAvailableSalonServicesAPI")
    @GetMapping(path = "retrieveAvailableSalonServices")
    public List<SalonServiceDetail> allAvailableSalonServices() {
        return salonServiceDetailRepository.findAll();
    }
    @Tag(name = "RetrieveAvailableSlotsAPI", description = "RetrieveAvailableSlotsAPI")
    @GetMapping(path = "retrieveAvailableSlots/{salonServiceId}/{formattedDate}")
    @Transactional(readOnly = true)
    public List<Slot> allAvailableSlotsForAServiceOnADay(@PathVariable("salonServiceId") Long salonServiceId, @PathVariable("formattedDate") String formattedDate) {
        LocalDate beginSlotDate = LocalDate.parse(formattedDate);
        LocalDateTime beginSlotDateTime=beginSlotDate.atStartOfDay();
        LocalDate endSlotDate = beginSlotDate.plusDays(1L);
        LocalDateTime endSlotDateTime= endSlotDate.atStartOfDay();
        SalonServiceDetail availableService = salonServiceDetailRepository.findSalonServiceDetailById(salonServiceId);
        try (Stream<Slot> slotStream=slotRepository.findSlotsByAvailableServicesContainingAndSlotForBetween(availableService,beginSlotDateTime,endSlotDateTime)){
            return slotStream.filter(slot -> slot.getStatus()== SlotStatus.AVAILABLE).toList();
        }

    }


}
