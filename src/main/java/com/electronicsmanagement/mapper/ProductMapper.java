package com.electronicsmanagement.mapper;

import org.springframework.stereotype.Component;

import com.electronicsmanagement.dto.response.ProductResponse;
import com.electronicsmanagement.entity.Product;


public class ProductMapper {
	
	public static ProductResponse toResponse(Product product, int availableStock) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setModelName(product.getModelName());
        response.setSpecifications(product.getSpecifications());
        response.setSellingPrice(product.getSellingPrice());
        response.setBrandName(product.getBrand().getName());
        response.setCategoryName(product.getCategory().getName());
        response.setAvailableStock(availableStock);
        return response;
    }

}
