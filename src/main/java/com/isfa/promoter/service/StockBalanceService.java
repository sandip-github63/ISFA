package com.isfa.promoter.service;

import org.springframework.http.ResponseEntity;

import com.isfa.promoter.model.StockBalanceRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StockBalanceResponse;

public interface StockBalanceService {

	ResponseEntity<?> getAllStockBalance();

	ResponseEntity<?> getStockBalanceById(Long id);

    BaseResponse<StockBalanceResponse> createStockBalance(StockBalanceRequest request);

    ResponseEntity<?> updateStockBalance(Long id, StockBalanceRequest request);

    ResponseEntity<?> deleteStockBalance(Long id);
	
}
