package com.electronicsmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronicsmanagement.dto.request.DealerRequest;
import com.electronicsmanagement.dto.response.DealerResponse;
import com.electronicsmanagement.entity.Brand;
import com.electronicsmanagement.entity.Dealer;
import com.electronicsmanagement.exception.BadRequestException;
import com.electronicsmanagement.exception.ResourceNotFoundException;
import com.electronicsmanagement.mapper.DealerMapper;
import com.electronicsmanagement.repository.BrandRepository;
import com.electronicsmanagement.repository.DealerRepository;
import com.electronicsmanagement.service.DealerService;


@Service
public class DealerServiceImpl implements DealerService {

	
	@Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private BrandRepository brandRepository;
    
    
	@Override
	public DealerResponse createDealer(DealerRequest request) {
		// TODO Auto-generated method stub
		if (dealerRepository.existsByBrandId(request.getBrandId())) {
            throw new BadRequestException("Dealer already exists for this brand");
        }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        Dealer dealer = new Dealer();
        dealer.setName(request.getName());
        dealer.setBrand(brand);
        dealer.setContactPerson(request.getContactPerson());
        dealer.setPhoneNumber(request.getPhoneNumber());
        dealer.setEmail(request.getEmail());
        dealer.setAddress(request.getAddress());
        dealer.setActive(true);

        return DealerMapper.toResponse(dealerRepository.save(dealer));
	}

	@Override
	public List<DealerResponse> getAllDealers() {
		// TODO Auto-generated method stub
		return dealerRepository.findAll()
                .stream()
                .map(DealerMapper::toResponse)
                .collect(Collectors.toList());
	}

	@Override
	public DealerResponse getDealerByBrand(Long brandId) {
		// TODO Auto-generated method stub
		Dealer dealer = dealerRepository.findByBrandId(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found for brand"));
        return DealerMapper.toResponse(dealer);
	}

	@Override
	public void deactivateDealer(Long id) {
		// TODO Auto-generated method stub
		Dealer dealer = dealerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found"));
        dealer.setActive(false);
        dealerRepository.save(dealer);
		
	}

	@Override
	public void activateDealer(Long id) {
		// TODO Auto-generated method stub
		Dealer dealer = dealerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found"));
        dealer.setActive(true);
        dealerRepository.save(dealer);
		
	}

}
