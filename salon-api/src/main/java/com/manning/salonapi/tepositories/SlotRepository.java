package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, Long> {
}
