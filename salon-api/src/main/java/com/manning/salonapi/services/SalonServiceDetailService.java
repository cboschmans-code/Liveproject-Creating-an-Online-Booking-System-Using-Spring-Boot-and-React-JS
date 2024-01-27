package com.manning.salonapi.services;

import com.manning.salonapi.entities.SalonServiceDetail;
import com.manning.salonapi.tepositories.SalonServiceDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceDetailService {

    private final SalonServiceDetailRepository salonServiceDetailRepository;

    public SalonServiceDetailService(SalonServiceDetailRepository salonServiceDetailRepository) {
        this.salonServiceDetailRepository = salonServiceDetailRepository;
    }

  public List<SalonServiceDetail> findAllServices(){
        return salonServiceDetailRepository.findAll();
    }
}
