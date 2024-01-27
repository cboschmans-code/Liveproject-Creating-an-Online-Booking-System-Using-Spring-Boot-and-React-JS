package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
