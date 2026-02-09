package com.electronicsmanagement.service;

import java.util.List;

import com.electronicsmanagement.dto.request.ProductRequest;
import com.electronicsmanagement.dto.response.ProductResponse;

public interface ProductService {
	
	ProductResponse createProduct(ProductRequest request);

  List<ProductResponse> getProductsByBrand(String brandName);
	
  ProductResponse updateProduct(Long id, ProductRequest request);

  ProductResponse getProductById(Long id);

  void deactivateProduct(Long id);

   void activateProduct(Long id);

   List<ProductResponse> getAllActiveProducts();

}
