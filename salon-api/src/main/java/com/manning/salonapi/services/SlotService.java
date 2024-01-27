package com.manning.salonapi.services;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import com.manning.salonapi.tepositories.SalonServiceDetailRepository;
import com.manning.salonapi.tepositories.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SlotService {
    private final SlotRepository slotRepository;
    private final SalonServiceDetailRepository salonServiceDetailRepository;

    public SlotService(SlotRepository slotRepository, SalonServiceDetailRepository salonServiceDetailRepository) {
        this.slotRepository = slotRepository;
        this.salonServiceDetailRepository = salonServiceDetailRepository;
    }

    public List<Slot> findAllAvaibleSlotsForAServiceOnADay(Long salonServiceId, String formattedDate) {
        LocalDate beginSlotDate = LocalDate.parse(formattedDate);
        LocalDateTime beginSlotDateTime = beginSlotDate.atStartOfDay();
        LocalDate endSlotDate = beginSlotDate.plusDays(1L);
        LocalDateTime endSlotDateTime = endSlotDate.atStartOfDay();
        SalonServiceDetail availableService = salonServiceDetailRepository.findSalonServiceDetailById(salonServiceId);
        try (Stream<Slot> slotStream = slotRepository.findSlotsByAvailableServicesContainingAndSlotForBetween(availableService, beginSlotDateTime, endSlotDateTime)) {
            return slotStream.filter(slot -> slot.getStatus() == SlotStatus.AVAILABLE).toList();
        }

    }
}

