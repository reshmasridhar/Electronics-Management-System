package com.electronicsmanagement.dto.request;

import java.math.BigDecimal;

public class ProductRequest {
	
	private String modelName;
    private String specifications;
    private BigDecimal sellingPrice;
    private Long brandId;
    private Long categoryId;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
    
	    

}
