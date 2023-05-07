package com.isfa.clientadminpanel.promoter.service;

import org.springframework.http.ResponseEntity;

public interface ProductDetailService {

	ResponseEntity<?> getInventory(Long userId, Long companyId, Long storeId);
	
}
