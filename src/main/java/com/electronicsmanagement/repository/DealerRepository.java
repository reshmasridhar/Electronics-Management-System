package com.electronicsmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicsmanagement.entity.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

    boolean existsByBrandId(Long brandId);

    Optional<Dealer> findByBrandId(Long brandId);
}
