package com.ninos.payments.stripe;

import com.ninos.payments.stripe.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/pay")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createPaymentIntent(paymentRequest));
    }

    @PutMapping("/update")
    public void updatePaymentBooking(@RequestBody PaymentRequest paymentRequest) {
        paymentService.updatePaymentBooking(paymentRequest);
    }


}
