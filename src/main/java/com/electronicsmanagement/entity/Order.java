package com.electronicsmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.electronicsmanagement.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String orderNumber;

	    private String customerName;

	    private String customerPhone;

	    private LocalDateTime orderDate;

	    @Enumerated(EnumType.STRING)
	    private OrderStatus status;

	    private BigDecimal totalAmount;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> items;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerPhone() {
			return customerPhone;
		}

		public void setCustomerPhone(String customerPhone) {
			this.customerPhone = customerPhone;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public BigDecimal getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}

		public List<OrderItem> getItems() {
			return items;
		}

		public void setItems(List<OrderItem> items) {
			this.items = items;
		}
	    
	    
	    

}
