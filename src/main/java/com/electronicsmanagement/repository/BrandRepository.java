package com.electronicsmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicsmanagement.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByCategoryId(Long categoryId);

    List<Brand> findByActiveTrue();

    boolean existsByNameAndCategoryId(String name, Long categoryId);
}
