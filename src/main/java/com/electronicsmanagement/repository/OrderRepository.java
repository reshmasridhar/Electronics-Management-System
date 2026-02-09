package com.electronicsmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicsmanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
	
	Optional<Order> findByOrderNumber(String orderNumber);

	List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
	

}
