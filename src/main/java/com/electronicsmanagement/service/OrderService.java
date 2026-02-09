package com.electronicsmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.electronicsmanagement.dto.request.OrderRequest;
import com.electronicsmanagement.dto.response.OrderResponse;

public interface OrderService {

	OrderResponse placeOrder(OrderRequest request);
	
	OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getAllOrders();

    void cancelOrder(Long orderId);

    List<OrderResponse> getOrdersByDate(LocalDate date);
}
