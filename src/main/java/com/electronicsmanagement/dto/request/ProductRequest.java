package com.electronicsmanagement.dto.request;

public class ProductRequest {
	
	 private String name;
	    private String modelNumber;
	    private String serialNumber;
	    private Double price;
	    private Long categoryId;
	    private Long brandId;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getModelNumber() {
			return modelNumber;
		}
		public void setModelNumber(String modelNumber) {
			this.modelNumber = modelNumber;
		}
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public Long getBrandId() {
			return brandId;
		}
		public void setBrandId(Long brandId) {
			this.brandId = brandId;
		}
	    
	    

}
