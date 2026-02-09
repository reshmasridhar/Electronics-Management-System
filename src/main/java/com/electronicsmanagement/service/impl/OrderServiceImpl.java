package com.electronicsmanagement.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.OrderItemRequest;
import com.electronicsmanagement.dto.request.OrderRequest;
import com.electronicsmanagement.dto.response.OrderResponse;
import com.electronicsmanagement.entity.Order;
import com.electronicsmanagement.entity.OrderItem;
import com.electronicsmanagement.entity.OrderStock;
import com.electronicsmanagement.entity.Product;
import com.electronicsmanagement.entity.Stock;
import com.electronicsmanagement.enums.OrderStatus;
import com.electronicsmanagement.enums.StockStatus;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.OrderMapper;
import com.electronicsmanagement.repository.OrderRepository;
import com.electronicsmanagement.repository.ProductRepository;
import com.electronicsmanagement.repository.StockRepository;
import com.electronicsmanagement.service.OrderService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED); // ✅ FIXED
        order.setCustomerName(request.getCustomerName());
        order.setCustomerPhone(request.getCustomerPhone());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            List<Stock> availableStocks =
                    stockRepository.findAvailableStockByProduct(product.getId());

            if (availableStocks.size() < itemReq.getQuantity()) {
                throw new BadRequestException(
                        "Insufficient stock for model: " + product.getModelName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(product.getSellingPrice());

            List<OrderStock> orderStocks = new ArrayList<>();

            for (int i = 0; i < itemReq.getQuantity(); i++) {
                Stock stock = availableStocks.get(i);
                stock.setStatus(StockStatus.SOLD);

                OrderStock os = new OrderStock();
                os.setOrderItem(orderItem);
                os.setStock(stock);

                orderStocks.add(os);
            }

            orderItem.setAllocatedStocks(orderStocks);
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(
                    product.getSellingPrice()
                           .multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        return OrderMapper.toResponse(orderRepository.save(order));
    }

	@Override
	public OrderResponse getOrderById(Long orderId) {
		Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id " + orderId));

        return OrderMapper.toResponse(order);
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
        return OrderMapper.toResponseList(orders);
	}

	@Override
	public void cancelOrder(Long orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id " + orderId));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new BadRequestException("Only CREATED orders can be cancelled");
        }

        // Release stock
        for (OrderItem item : order.getItems()) {
            for (OrderStock os : item.getAllocatedStocks()) {
                Stock stock = os.getStock();
                stock.setStatus(StockStatus.AVAILABLE);
            }
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
	}

	@Override
	public List<OrderResponse> getOrdersByDate(LocalDate date) {
		// TODO Auto-generated method stub
		LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        List<Order> orders = orderRepository.findByOrderDateBetween(start, end);
        return OrderMapper.toResponseList(orders);
	}
}
