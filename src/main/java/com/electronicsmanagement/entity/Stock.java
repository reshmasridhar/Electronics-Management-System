package com.electronicsmanagement.entity;

import java.time.LocalDateTime;

import com.electronicsmanagement.enums.StockStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "stocks", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private StockStatus status = StockStatus.AVAILABLE;

    private LocalDateTime addedDate = LocalDateTime.now();

    public Long getId() { return id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public StockStatus getStatus() { return status; }
    public void setStatus(StockStatus status) { this.status = status; }
}
