package com.electronicsmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.CategoryRequest;
import com.electronicsmanagement.dto.response.CategoryResponse;
import com.electronicsmanagement.entity.Category;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.CategoryMapper;
import com.electronicsmanagement.repository.CategoryRepository;
import com.electronicsmanagement.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{


    @Autowired
    private CategoryRepository categoryRepository;

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {
		// TODO Auto-generated method stub
		categoryRepository.findByName(request.getName())
        .ifPresent(c -> {
            throw new BadRequestException("Category already exists");
        });

    Category category = CategoryMapper.toEntity(request);
    Category saved = categoryRepository.save(category);

    return CategoryMapper.toResponse(saved);
	}

	@Override
	public CategoryResponse updateCategory(Long id, CategoryRequest request) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(request.getName());

        return CategoryMapper.toResponse(categoryRepository.save(category));
    }

	@Override
	public CategoryResponse getCategoryById(Long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return CategoryMapper.toResponse(category);
    }

	@Override
	public List<CategoryResponse> getAllCategories() {
		// TODO Auto-generated method stub
		 return categoryRepository.findByActiveTrue()
	                .stream()
	                .map(CategoryMapper::toResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public void deactivateCategory(Long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setActive(false);
        categoryRepository.save(category);
	}

	@Override
	public CategoryResponse activateCategory(Long id) {
	    Category category = categoryRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

	    category.setActive(true);
	    categoryRepository.save(category);

	    return CategoryMapper.toResponse(category);
	}
	
	

}
