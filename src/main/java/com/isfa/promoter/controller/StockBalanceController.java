package com.isfa.promoter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.promoter.model.StockBalanceRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StockBalanceResponse;
import com.isfa.promoter.service.StockBalanceService;




@RestController
@RequestMapping("/api")
public class StockBalanceController {

	private static final Logger logger = LoggerFactory.getLogger(StockBalanceController.class);

	
	@Autowired
	StockBalanceService balanceService;
	
	@GetMapping("/stock-balance")
	public ResponseEntity<?> getAllStockBalance() {
		logger.info("StockBalance Controller Getting All StockBalance executing");
		return this.balanceService.getAllStockBalance();
	}


	@GetMapping("/stock-balance/{id}")
	public ResponseEntity<?> getStockBalanceById(@PathVariable("id") Long id) {
		logger.info("StockBalance Controller Getting StockBalance By Id executing");
			return balanceService.getStockBalanceById(id);
	}



	@PostMapping("/stock-balance")
	public ResponseEntity<?> createStockBalance(@RequestBody StockBalanceRequest request) {
			logger.info("StockBalance Controller Saving New StockBalance executing");
			BaseResponse<StockBalanceResponse> response = balanceService.createStockBalance(request);
			logger.info("StockBalance Service Saving New StockBalance completed");
			 logger.info("StockBalance Controller Saving New StockBalance completed");
			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/stock-balance/{id}")
	public ResponseEntity<?> updateStockBalance(@PathVariable("id") Long id, @RequestBody StockBalanceRequest request) {
		logger.info("StockBalance Controller Updating Existing StockBalance executing");	
		return balanceService.updateStockBalance(id, request);
	}


	@DeleteMapping("/stock-balance/{id}")
	public ResponseEntity<?> deleteStockBalance(@PathVariable("id") Long id) {
		logger.info("StockBalance Controller Deleting Existing StockBalance executing");
		return balanceService.deleteStockBalance(id);
	}

	
}
