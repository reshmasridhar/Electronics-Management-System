package com.electronicsmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.ProductRequest;
import com.electronicsmanagement.dto.response.ProductResponse;
import com.electronicsmanagement.entity.Product;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.ProductMapper;
import com.electronicsmanagement.repository.BrandRepository;
import com.electronicsmanagement.repository.CategoryRepository;
import com.electronicsmanagement.repository.ProductRepository;
import com.electronicsmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductMapper productMapper;
    
    
	@Override
	public ProductResponse createProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		if (productRepository.findBySerialNumber(request.getSerialNumber()).isPresent()) {
            throw new BadRequestException("Serial number already exists");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setModelNumber(request.getModelNumber());
        product.setSerialNumber(request.getSerialNumber());
        product.setPrice(request.getPrice());

        product.setCategory(
            categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"))
        );

        product.setBrand(
            brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"))
        );

        return productMapper.toResponse(productRepository.save(product));
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductRequest request) {
		// TODO Auto-generated method stub
		Product product = getProduct(id);

        product.setName(request.getName());
        product.setModelNumber(request.getModelNumber());
        product.setPrice(request.getPrice());

        return productMapper.toResponse(productRepository.save(product));
	}

	@Override
	public ProductResponse getProductById(Long id) {
		// TODO Auto-generated method stub
		return productMapper.toResponse(getProduct(id));
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> getActiveProducts() {
		// TODO Auto-generated method stub
		return productRepository.findByActiveTrue()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
	}

	@Override
	public void deactivateProduct(Long id) {
		// TODO Auto-generated method stub
		Product product = getProduct(id);
        product.setActive(false);
        productRepository.save(product);
		
	}

	@Override
	public void activateProduct(Long id) {
		// TODO Auto-generated method stub
		Product product = getProduct(id);
        product.setActive(true);
        productRepository.save(product);
		
	}
	
	private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
