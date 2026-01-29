package com.electronicsmanagement.mapper;

import org.springframework.stereotype.Component;

import com.electronicsmanagement.dto.response.ProductResponse;
import com.electronicsmanagement.entity.Product;

@Component
public class ProductMapper {
	
	public ProductResponse toResponse(Product product) {
		ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setModelNumber(product.getModelNumber());
        response.setSerialNumber(product.getSerialNumber());
        response.setPrice(product.getPrice());
        response.setActive(product.isActive());
        response.setCategoryName(product.getCategory().getName());
        response.setBrandName(product.getBrand().getName());
        return response;
    }

}
