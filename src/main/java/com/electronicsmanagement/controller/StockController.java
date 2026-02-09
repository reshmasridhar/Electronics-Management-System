package com.electronicsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicsmanagement.dto.request.StockRequest;
import com.electronicsmanagement.service.impl.StockServiceImpl;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	@Autowired
    private StockServiceImpl stockService;

	
    @PostMapping
    public ResponseEntity<String> addStock(@RequestBody StockRequest request) {
        stockService.addStock(request);
        return ResponseEntity.ok("Stock added successfully");
    }

    
    @GetMapping("/available/{productId}")
    public ResponseEntity<Integer> getAvailableStock(@PathVariable Long productId) {
        return ResponseEntity.ok(stockService.getAvailableStockCount(productId));
    }
    
 
    @PatchMapping("/{stockId}/damage")
    public ResponseEntity<Void> markDamaged(@PathVariable Long stockId) {
        stockService.markStockAsDamaged(stockId);
        return ResponseEntity.noContent().build();
    }
	
	
	


}
