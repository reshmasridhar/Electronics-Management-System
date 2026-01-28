package com.electronicsmanagement.mapper;

import com.electronicsmanagement.dto.response.DealerResponse;
import com.electronicsmanagement.entity.Dealer;

public class DealerMapper {
	
	public static DealerResponse toResponse(Dealer dealer) {
        DealerResponse response = new DealerResponse();
        response.setId(dealer.getId());
        response.setName(dealer.getName());
        response.setBrandId(dealer.getBrand().getId());
        response.setBrandName(dealer.getBrand().getName());
        response.setContactPerson(dealer.getContactPerson());
        response.setPhoneNumber(dealer.getPhoneNumber());
        response.setEmail(dealer.getEmail());
        response.setAddress(dealer.getAddress());
        response.setActive(dealer.getActive());
        return response;
    }

}
