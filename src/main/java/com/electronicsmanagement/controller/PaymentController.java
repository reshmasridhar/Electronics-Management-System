package com.electronicsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicsmanagement.dto.request.PaymentConfirmRequest;
import com.electronicsmanagement.dto.request.PaymentRequest;
import com.electronicsmanagement.dto.response.PaymentResponse;
import com.electronicsmanagement.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public PaymentResponse initiatePayment(@RequestBody PaymentRequest request) {
        return paymentService.initiatePayment(request);
    }

    @PostMapping("/confirm")
    public PaymentResponse confirmPayment(@RequestBody PaymentConfirmRequest request) {
        return paymentService.confirmPayment(request);
    }
	
}
