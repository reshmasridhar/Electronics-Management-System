package com.electronicsmanagement.mapper;

import com.electronicsmanagement.dto.response.PaymentResponse;
import com.electronicsmanagement.entity.Payment;

public class PaymentMapper {
	
	public static PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentRef(payment.getPaymentRef());
        response.setAmount(payment.getAmount());
        response.setMethod(payment.getMethod());
        response.setStatus(payment.getStatus());
        return response;
    }

}
