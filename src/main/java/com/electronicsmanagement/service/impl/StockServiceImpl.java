package com.electronicsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.StockRequest;
import com.electronicsmanagement.entity.Product;
import com.electronicsmanagement.entity.Stock;
import com.electronicsmanagement.enums.StockStatus;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.repository.ProductRepository;
import com.electronicsmanagement.repository.StockRepository;

@Service
public class StockServiceImpl {
	
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    public void addStock(StockRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        for (String serial : request.getSerialNumbers()) {
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setSerialNumber(serial);
            stock.setStatus(StockStatus.AVAILABLE);
            stockRepository.save(stock);
        }
    }
    
    public int getAvailableStockCount(Long productId) {
        return stockRepository.countAvailableStock(productId);
    }
    
    public void markStockAsDamaged(Long stockId) {

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));

        stock.setStatus(StockStatus.DAMAGED);
        stockRepository.save(stock);
    }
    
    // USED DURING ORDER
    public Stock pickAvailableStock(Long productId) {

        Stock stock = stockRepository
                .findFirstByProduct_IdAndStatus(productId, StockStatus.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("Out of stock"));

        stock.setStatus(StockStatus.SOLD);
        return stockRepository.save(stock);
    }

}
