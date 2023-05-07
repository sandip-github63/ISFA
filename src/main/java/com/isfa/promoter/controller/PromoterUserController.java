/*package com.isfa.promoter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.promoter.model.StockAssignmentRequest;
import com.isfa.promoter.model.StockBalanceRequest;
import com.isfa.promoter.model.StoreProductMappingRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StockAssignmentResponse;
import com.isfa.promoter.response.StockBalanceResponse;
import com.isfa.promoter.response.StoreProductMappingResponse;
import com.isfa.promoter.service.StockAssignmentService;
import com.isfa.promoter.service.StockBalanceService;
import com.isfa.promoter.service.StoreProductMappingService;

@RestController
@RequestMapping("/api")
public class PromoterUserController {


	@Autowired
	StoreProductMappingService mappingService;
	
	@Autowired
	StockBalanceService balanceService;
	
	@Autowired
	StockAssignmentService assignmentService;
	
	@PostMapping("/store-product")
	public ResponseEntity<?> storeProductMappingController(@RequestBody StoreProductMappingRequest request ){
		String task = request.getTask();
		Long id = request.getStoreProductId();
		
		if("delete".equalsIgnoreCase(task)) {
			return mappingService.deleteStoreProduct(id);
		}
		else if("update".equalsIgnoreCase(task)) {
			return mappingService.updateStoreProduct(id, request);
		}
		else {
			BaseResponse<StoreProductMappingResponse> response = mappingService.createStoreProduct(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/stock-balance")
	public ResponseEntity<?> stockBalanceController(@RequestBody StockBalanceRequest request ){
		String task = request.getTask();
		Long id = request.getId();
		
		if("get".equalsIgnoreCase(task)) {
			return balanceService.getStockBalanceById(id);
		}
		else if("create".equalsIgnoreCase(task)) {
			BaseResponse<StockBalanceResponse> response = balanceService.createStockBalance(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else if("update".equalsIgnoreCase(task)) {
			return balanceService.updateStockBalance(id,request);
		}
		else if("delete".equalsIgnoreCase(task)) {
			return balanceService.deleteStockBalance(id);
		}
		else {
			return balanceService.getAllStockBalance();
		}
	}
	
	
	@PostMapping("/stock-assignment")
	public ResponseEntity<?> stockAssignmentController(@RequestBody StockAssignmentRequest request ){
		String task = request.getTask();
		Long id = request.getTransId();
		
		if("get".equalsIgnoreCase(task)) {
			return assignmentService.getStockAssignment(id);
		}
		else if("create".equalsIgnoreCase(task)) {
			BaseResponse<StockAssignmentResponse> response = assignmentService.createStockAssignment(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else if("update".equalsIgnoreCase(task)) {
			return assignmentService.updateStockAssignment(id,request);
		}
		else if("delete".equalsIgnoreCase(task)) {
			return assignmentService.deleteStockAssignment(id);
		}
		else {
			return assignmentService.getAllStockAssignment();
		}
	}
	
	
}
*/
