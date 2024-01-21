package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, Long> {
   Stream<Slot> findSlotsByAvailableServicesContainingAndSlotForBetween(SalonServiceDetail wantedSalonService, LocalDateTime slotFor, LocalDateTime slotFor2);
}
