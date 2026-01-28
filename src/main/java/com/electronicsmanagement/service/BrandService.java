package com.electronicsmanagement.service;

import java.util.List;

import com.electronicsmanagement.dto.request.BrandRequest;
import com.electronicsmanagement.dto.response.BrandResponse;

public interface BrandService {

	BrandResponse createBrand(BrandRequest request);

    List<BrandResponse> getAllBrands();

    List<BrandResponse> getBrandsByCategory(Long categoryId);

    void deactivateBrand(Long id);

    void activateBrand(Long id);
}
