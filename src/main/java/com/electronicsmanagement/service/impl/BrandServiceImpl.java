package com.electronicsmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.BrandRequest;
import com.electronicsmanagement.dto.response.BrandResponse;
import com.electronicsmanagement.entity.Brand;
import com.electronicsmanagement.entity.Category;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.BrandMapper;
import com.electronicsmanagement.repository.BrandRepository;
import com.electronicsmanagement.repository.CategoryRepository;
import com.electronicsmanagement.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	
	
	@Override
	public BrandResponse createBrand(BrandRequest request) {
		// TODO Auto-generated method stub
		if (brandRepository.existsByNameAndCategoryId(
                request.getName(), request.getCategoryId())) {
            throw new BadRequestException("Brand already exists for this category");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setCategory(category);
        brand.setActive(true);

        return BrandMapper.toResponse(brandRepository.save(brand));
	}

	@Override
	public List<BrandResponse> getAllBrands() {
		// TODO Auto-generated method stub
		return brandRepository.findAll()
                .stream()
                .map(BrandMapper::toResponse)
                .collect(Collectors.toList());
	}

	@Override
	public List<BrandResponse> getBrandsByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		 return brandRepository.findByCategoryId(categoryId)
	                .stream()
	                .map(BrandMapper::toResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public void deactivateBrand(Long id) {
		// TODO Auto-generated method stub
		Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.setActive(false);
        brandRepository.save(brand);
		
	}

	@Override
	public void activateBrand(Long id) {
		// TODO Auto-generated method stub
		Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        brand.setActive(true);
        brandRepository.save(brand);
		
	}

}
