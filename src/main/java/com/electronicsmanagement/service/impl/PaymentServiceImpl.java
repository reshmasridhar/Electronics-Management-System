package com.electronicsmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.PaymentConfirmRequest;
import com.electronicsmanagement.dto.request.PaymentRequest;
import com.electronicsmanagement.dto.response.PaymentResponse;
import com.electronicsmanagement.entity.Order;
import com.electronicsmanagement.entity.Payment;
import com.electronicsmanagement.enums.OrderStatus;
import com.electronicsmanagement.enums.PaymentStatus;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.PaymentMapper;
import com.electronicsmanagement.repository.OrderRepository;
import com.electronicsmanagement.repository.PaymentRepository;
import com.electronicsmanagement.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	
	 @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;
	    
	    
	@Override
	public PaymentResponse initiatePayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new BadRequestException("Payment not allowed for this order");
        }

        Payment payment = new Payment();
        payment.setPaymentRef(UUID.randomUUID().toString());
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatus.INITIATED);
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);
        return PaymentMapper.toResponse(payment);
	}

	@Override
	public PaymentResponse confirmPayment(PaymentConfirmRequest request) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findByPaymentRef(request.getPaymentRef())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        Order order = payment.getOrder();

        if (request.isSuccess()) {
            payment.setStatus(PaymentStatus.SUCCESS);
            order.setStatus(OrderStatus.PAID);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }

        paymentRepository.save(payment);
        orderRepository.save(order);

        return PaymentMapper.toResponse(payment);
	}

}
