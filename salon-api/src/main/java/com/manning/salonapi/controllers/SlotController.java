package com.manning.salonapi.controllers;

import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.services.SlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Slot")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/retrieveAvailableSlots/{salonServiceId}/{formattedDate}")
    @Operation(summary = "RetrieveAvailableSlotsAPI")
    public List<Slot> retrieveAvailableSlotsAPI(@PathVariable Long salonServiceId,
                                                @Parameter(description = "Date in yyyy-MM-dd format", required = true) @Schema(defaultValue = "2020-11-21") @PathVariable String formattedDate) {
        return slotService.getSlotsForServiceOnDate(salonServiceId, formattedDate);
    }


}
