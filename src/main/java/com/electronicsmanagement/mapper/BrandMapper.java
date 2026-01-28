package com.electronicsmanagement.mapper;

import com.electronicsmanagement.dto.response.BrandResponse;
import com.electronicsmanagement.entity.Brand;

public class BrandMapper {
	
	public static BrandResponse toResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setCategoryId(brand.getCategory().getId());
        response.setCategoryName(brand.getCategory().getName());
        response.setActive(brand.getActive());
        return response;
    }

}
