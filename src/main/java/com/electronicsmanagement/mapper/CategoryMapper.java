package com.electronicsmanagement.mapper;

import com.electronicsmanagement.dto.request.CategoryRequest;
import com.electronicsmanagement.dto.response.CategoryResponse;
import com.electronicsmanagement.entity.Category;

public class CategoryMapper {
	
	public static Category toEntity(CategoryRequest request)
	{
		Category category = new Category();
		category.setName(request.getName());
		return category;
		
		
		
	}
	
	public static CategoryResponse toResponse(Category category)
	{
		CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setActive(category.getActive());
        response.setCreatedAt(category.getCreatedAt());
        return response;
		
	}

}
