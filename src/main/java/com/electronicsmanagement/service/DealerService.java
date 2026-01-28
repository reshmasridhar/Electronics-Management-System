package com.electronicsmanagement.service;

import java.util.List;

import com.electronicsmanagement.dto.request.DealerRequest;
import com.electronicsmanagement.dto.response.DealerResponse;

public interface DealerService {
	
	DealerResponse createDealer(DealerRequest request);

    List<DealerResponse> getAllDealers();

    DealerResponse getDealerByBrand(Long brandId);

    void deactivateDealer(Long id);

    void activateDealer(Long id);

}
