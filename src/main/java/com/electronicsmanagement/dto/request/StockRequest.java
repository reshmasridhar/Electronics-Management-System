package com.electronicsmanagement.dto.request;

import java.util.List;

public class StockRequest {

	private Long productId;
    private List<String> serialNumbers;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public List<String> getSerialNumbers() { return serialNumbers; }
    public void setSerialNumbers(List<String> serialNumbers) {
        this.serialNumbers = serialNumbers;
    }
}
