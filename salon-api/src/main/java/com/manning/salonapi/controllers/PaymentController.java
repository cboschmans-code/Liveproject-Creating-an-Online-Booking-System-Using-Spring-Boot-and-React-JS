package com.manning.salonapi.controllers;

import com.manning.salonapi.dto.ClientSecretResponse;
import com.manning.salonapi.dto.PaymentRequest;
import com.manning.salonapi.exceptions.SalonServiceDetailNotFoundException;
import com.manning.salonapi.exceptions.SlotNotAvailableException;
import com.manning.salonapi.exceptions.SlotNotFoundException;
import com.manning.salonapi.services.PaymentService;
import com.manning.salonapi.tepositories.PaymentRepository;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/payments", produces = "application/json")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/initiate", consumes = "application/json")
    @Operation(summary = "InitiatePaymentAPI")
    public ClientSecretResponse initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            return new ClientSecretResponse(paymentService.initiatePayment(paymentRequest).getClientSecret());
        } catch (StripeException se) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, se.getMessage(), se);
        } catch (SlotNotFoundException sle){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "slot id was wrong, no slot found", sle);
        }
        catch (SlotNotAvailableException slnae){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "slot not available", slnae);
        }catch (SalonServiceDetailNotFoundException ssde){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "salon service id was wrong, no salon service found", ssde);
        }
    }

}
