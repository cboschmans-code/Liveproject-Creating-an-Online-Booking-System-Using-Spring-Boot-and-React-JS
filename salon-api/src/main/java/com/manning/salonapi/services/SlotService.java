package com.manning.salonapi.services;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import com.manning.salonapi.tepositories.SlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class SlotService {

    SlotRepository slotRepository;


    SalonService salonService;


    public SlotService(SalonService salonService, SlotRepository slotRepository) {
        this.salonService = salonService;
        this.slotRepository = slotRepository;
    }

    public List<Slot> getSlotsForServiceOnDate(Long slotServiceId, String formattedDate) {
        SalonServiceDetail salonServiceDetail = salonService.findById(slotServiceId).orElseThrow(() -> new RuntimeException("Invalid Service"));

        LocalDate localDate = getAsDate(formattedDate);

        LocalDateTime startDate = localDate.atStartOfDay();
        LocalDateTime endDate = localDate.plusDays(1).atStartOfDay();
        log.info("Querying for  " + slotServiceId + " From " + startDate + " to " + endDate);

        List<Slot> results = slotRepository.findSlotsByAvailableServicesContainingAndSlotForBetweenAndStatus(salonServiceDetail, startDate, endDate, SlotStatus.AVAILABLE);
        log.info("returning  " + results.size() + " Slots");

        return results;
    }

    public LocalDate getAsDate(String formattedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(formattedDate, formatter);
    }
}
