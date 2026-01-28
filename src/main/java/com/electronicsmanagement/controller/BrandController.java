package com.electronicsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicsmanagement.dto.request.BrandRequest;
import com.electronicsmanagement.dto.response.BrandResponse;
import com.electronicsmanagement.service.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	@Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(
            @RequestBody BrandRequest request) {
        return new ResponseEntity<>(
                brandService.createBrand(request),
                HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BrandResponse>> getBrandsByCategory(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(
                brandService.getBrandsByCategory(categoryId));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateBrand(@PathVariable Long id) {
        brandService.deactivateBrand(id);
        return ResponseEntity.ok("Brand with id " + id + " successfully deactivated");
    }
    
    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateBrand(@PathVariable Long id) {
        brandService.activateBrand(id);
        return ResponseEntity.ok("Brand with id " + id + " successfully activated");
    }
    
    

}
