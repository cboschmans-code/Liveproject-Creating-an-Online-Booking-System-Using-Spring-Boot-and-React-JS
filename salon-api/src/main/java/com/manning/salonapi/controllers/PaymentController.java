package com.manning.salonapi.controllers;

import com.manning.salonapi.config.SalonDetails;
import com.manning.salonapi.dto.ClientSecretResponse;
import com.manning.salonapi.dto.PaymentRequest;
import com.manning.salonapi.dto.PaymentResponse;
import com.manning.salonapi.exceptions.*;
import com.manning.salonapi.services.PaymentService;
import com.manning.salonapi.tepositories.PaymentRepository;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/payments", produces = "application/json")
public class PaymentController {

    private final PaymentService paymentService;

    private  final SalonDetails salonDetails;

    public PaymentController(PaymentService paymentService, SalonDetails salonDetails) {
        this.paymentService = paymentService;
        this.salonDetails = salonDetails;
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

    @PutMapping(path = "/confirm/{paymentId}")
    @Operation(summary = "VerifyPaymentAndConfirmSlotAPI")
    public PaymentResponse confirmPayment(@PathVariable String paymentId){
        try {
            return new PaymentResponse(paymentService.confirmPayment(paymentId), salonDetails.clone());
        } catch(PaymentNotFoundException pnfe){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "payment not found", pnfe);
        } catch (StripeException se){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,se.getMessage(),se);
        } catch (PaymentNotSucceededException pnse){
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED,"payment did not succeed",pnse);
        }
        }

}
