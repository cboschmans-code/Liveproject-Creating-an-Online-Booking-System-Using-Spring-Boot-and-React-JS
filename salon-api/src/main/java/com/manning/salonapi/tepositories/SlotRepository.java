package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import com.manning.salonapi.entities.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, Long> {
    List<Slot> findSlotsByAvailableServicesContainingAndSlotForBetweenAndStatus(SalonServiceDetail wantedService, LocalDateTime beginTime, LocalDateTime endTime, SlotStatus slotStatus);
}
