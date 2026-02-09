package com.electronicsmanagement.dto.request;

public class PaymentConfirmRequest {

	private String paymentRef;
    private boolean success;
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
    
    
	
}
