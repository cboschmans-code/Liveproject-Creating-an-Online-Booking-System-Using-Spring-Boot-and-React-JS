package com.manning.salonapi.services;

import com.manning.salonapi.config.SalonDetails;
import com.manning.salonapi.dto.PaymentRequest;
import com.manning.salonapi.entities.*;
import com.manning.salonapi.exceptions.SalonServiceDetailNotFoundException;
import com.manning.salonapi.exceptions.SlotNotAvailableException;
import com.manning.salonapi.exceptions.SlotNotFoundException;
import com.manning.salonapi.tepositories.PaymentRepository;
import com.manning.salonapi.tepositories.SalonServiceDetailRepository;
import com.manning.salonapi.tepositories.SlotRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final SlotRepository slotRepository;

    private final SalonServiceDetailRepository salonServiceDetailRepository;

    private final SalonDetails salonDetails;

    public PaymentService(PaymentRepository paymentRepository, SlotRepository slotRepository, SalonServiceDetailRepository salonServiceDetailRepository, SalonDetails salonDetails) {
        this.paymentRepository = paymentRepository;
        this.slotRepository = slotRepository;
        this.salonServiceDetailRepository = salonServiceDetailRepository;
        this.salonDetails = salonDetails;
    }


    public Payment initiatePayment(PaymentRequest paymentRequest) throws SlotNotAvailableException, SlotNotFoundException, SalonServiceDetailNotFoundException, StripeException {
        Stripe.apiKey = salonDetails.getApiKey();
        Slot slot = slotRepository.findById(paymentRequest.slotID()).orElseThrow(SlotNotFoundException::new);
        SalonServiceDetail salonServiceDetail = salonServiceDetailRepository.findById(paymentRequest.salonServiceDetailID()).orElseThrow(SalonServiceDetailNotFoundException::new);
        if (slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new SlotNotAvailableException();
        }
        Long amount = salonServiceDetail.getPrice() * 100;
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("eur")
                        .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        Payment payment = paymentRepository.save(Payment.builder().selectedService(salonServiceDetail).slot(slot).amount(amount).status(PaymentStatus.PROCESSING).intentId(paymentIntent.getId())
                .clientSecret(paymentIntent.getClientSecret()).firstName(paymentRequest.firstName()).lastName(paymentRequest.lastName())
                .email(paymentRequest.email()).phoneNumber(paymentRequest.phoneNumber()).build());
        slot.setStatus(SlotStatus.LOCKED);
        slotRepository.save(slot);
        return  payment;
    }

}
