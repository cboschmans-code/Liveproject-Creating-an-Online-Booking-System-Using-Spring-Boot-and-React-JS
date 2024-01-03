package com.manning.salonapi.tepositories;

import com.manning.salonapi.entities.SalonServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonServiceDetailRepository extends JpaRepository< SalonServiceDetail,Long> {
}
