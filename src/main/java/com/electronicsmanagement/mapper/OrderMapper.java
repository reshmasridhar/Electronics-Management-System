package com.electronicsmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.electronicsmanagement.dto.response.OrderItemResponse;
import com.electronicsmanagement.dto.response.OrderResponse;
import com.electronicsmanagement.entity.Order;
import com.electronicsmanagement.entity.OrderItem;
import com.electronicsmanagement.entity.OrderStock;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItem item : order.getItems()) {

            OrderItemResponse ir = new OrderItemResponse();
            ir.setProductId(item.getProduct().getId());
            ir.setModelName(item.getProduct().getModelName());
            ir.setQuantity(item.getQuantity());
            ir.setPrice(item.getPrice());

            List<String> serials = new ArrayList<>();
            for (OrderStock os : item.getAllocatedStocks()) {
                serials.add(os.getStock().getSerialNumber());
            }

            ir.setSerialNumbers(serials);
            itemResponses.add(ir);
        }

        response.setItems(itemResponses);
        return response;
    }
    
    public static List<OrderResponse> toResponseList(List<Order> orders) {

        List<OrderResponse> responses = new ArrayList<>();
        for (Order order : orders) {
            responses.add(toResponse(order));
        }
        return responses;
    }
}
