package com.electronicsmanagement.dto.response;

import java.math.BigDecimal;

import com.electronicsmanagement.enums.PaymentMethod;
import com.electronicsmanagement.enums.PaymentStatus;

public class PaymentResponse {
	
	private String paymentRef;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public PaymentMethod getMethod() {
		return method;
	}
	public void setMethod(PaymentMethod method) {
		this.method = method;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
    
    

}
