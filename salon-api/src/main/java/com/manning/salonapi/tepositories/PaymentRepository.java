package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByIntentId(String intentId);
}
