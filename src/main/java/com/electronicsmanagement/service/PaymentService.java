package com.electronicsmanagement.service;

import com.electronicsmanagement.dto.request.PaymentConfirmRequest;
import com.electronicsmanagement.dto.request.PaymentRequest;
import com.electronicsmanagement.dto.response.PaymentResponse;

public interface PaymentService {

	 PaymentResponse initiatePayment(PaymentRequest request);

	 PaymentResponse confirmPayment(PaymentConfirmRequest request);
}
