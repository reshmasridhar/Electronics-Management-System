package com.electronicsmanagement.service;

import java.util.List;

import com.electronicsmanagement.dto.request.CategoryRequest;
import com.electronicsmanagement.dto.response.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    void deactivateCategory(Long id);
    
    CategoryResponse activateCategory(Long id);
    
    
	

}
