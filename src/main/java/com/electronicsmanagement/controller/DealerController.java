package com.electronicsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicsmanagement.dto.request.DealerRequest;
import com.electronicsmanagement.dto.response.DealerResponse;
import com.electronicsmanagement.service.DealerService;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin(origins = "http://localhost:5173")
public class DealerController {
	
	@Autowired
    private DealerService dealerService;

    @PostMapping
    public ResponseEntity<DealerResponse> createDealer(
            @RequestBody DealerRequest request) {
        return new ResponseEntity<>(
                dealerService.createDealer(request),
                HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<DealerResponse>> getAllDealers() {
        return ResponseEntity.ok(dealerService.getAllDealers());
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<DealerResponse> getDealerByBrand(
            @PathVariable Long brandId) {
        return ResponseEntity.ok(
                dealerService.getDealerByBrand(brandId));
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateDealer(@PathVariable Long id) {
        dealerService.deactivateDealer(id);
        return ResponseEntity.ok("Dealer with id " + id + " successfully deactivated");
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateDealer(@PathVariable Long id) {
        dealerService.activateDealer(id);
        return ResponseEntity.ok("Dealer with id " + id + " successfully activated");
    }
    

}
