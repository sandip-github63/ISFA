package com.isfa.promoter.service;

import org.springframework.http.ResponseEntity;

public interface StoreDetailService {

	ResponseEntity<?> getPromoterDetail(Long userId, Long companyId);

	
}
