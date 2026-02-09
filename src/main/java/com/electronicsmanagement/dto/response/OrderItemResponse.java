package com.electronicsmanagement.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemResponse {

	private Long productId;
    private String modelName;
    private int quantity;
    private BigDecimal price;
    private List<String> serialNumbers;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<String> getSerialNumbers() {
		return serialNumbers;
	}
	public void setSerialNumbers(List<String> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
    
    
}
