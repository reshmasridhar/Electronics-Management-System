package com.electronicsmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicsmanagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentRef(String paymentRef);

    Optional<Payment> findByOrder_Id(Long orderId);
}