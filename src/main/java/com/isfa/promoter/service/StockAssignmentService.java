package com.isfa.promoter.service;

import org.springframework.http.ResponseEntity;

import com.isfa.promoter.model.StockAssignmentRequest;

public interface StockAssignmentService {

	ResponseEntity<?> getAllStockAssignment();
	
	ResponseEntity<?> getStockAssignment(Long transId);
	
//	BaseResponse<StockAssignmentResponse>createStockAssignment(StockAssignmentRequest request);

	ResponseEntity<?> addInventory(Long userId, StockAssignmentRequest request);
	
	ResponseEntity<?> saleProduct(Long userId, StockAssignmentRequest request);
	
//	ResponseEntity<?> updateStockAssignment(Long transId, StockAssignmentRequest request);

	ResponseEntity<?> deleteStockAssignment(Long transId);



}
