package com.electronicsmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.ProductRequest;
import com.electronicsmanagement.dto.response.ProductResponse;
import com.electronicsmanagement.entity.Brand;
import com.electronicsmanagement.entity.Category;
import com.electronicsmanagement.entity.Product;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.ProductMapper;
import com.electronicsmanagement.repository.BrandRepository;
import com.electronicsmanagement.repository.CategoryRepository;
import com.electronicsmanagement.repository.ProductRepository;
import com.electronicsmanagement.repository.StockRepository;
import com.electronicsmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;
    
//    @Autowired
//    private ProductMapper productMapper;
    
    @Autowired
    private StockRepository stockRepository;
    
    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = new Product();
        product.setModelName(request.getModelName());
        product.setSpecifications(request.getSpecifications());
        product.setSellingPrice(request.getSellingPrice());
        product.setBrand(brand);
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return ProductMapper.toResponse(saved, 0);
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        int stock = stockRepository.countAvailableStock(id);
        return ProductMapper.toResponse(product, stock);
    }

    @Override
    public List<ProductResponse> getAllActiveProducts() {

        return productRepository.findAll()
                .stream()
                .map(p -> ProductMapper.toResponse(
                        p,
                        stockRepository.countAvailableStock(p.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByBrand(String brandName) {

        return productRepository.findByBrand_NameAndActiveTrue(brandName)
                .stream()
                .map(p -> ProductMapper.toResponse(
                        p,
                        stockRepository.countAvailableStock(p.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setModelName(request.getModelName());
        product.setSpecifications(request.getSpecifications());
        product.setSellingPrice(request.getSellingPrice());
        product.setBrand(brand);
        product.setCategory(category);

        Product updated = productRepository.save(product);
        int stock = stockRepository.countAvailableStock(id);

        return ProductMapper.toResponse(updated, stock);
    }

    @Override
    public void deactivateProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public void activateProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setActive(true);
        productRepository.save(product);
    }
}


