package com.isfa.promoter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.promoter.model.StockAssignmentRequest;
import com.isfa.promoter.service.StockAssignmentService;


@RestController
@RequestMapping("/api")
public class StockAssignmentController {

	private static final Logger logger = LoggerFactory.getLogger(StockAssignmentController.class);

	
	@Autowired
	StockAssignmentService assignmentService;
	
	@GetMapping("/stock-assignment")
	public ResponseEntity<?> getAllStockAssignments() {
		logger.info("Color Controller Getting All Colors executing");
		return this.assignmentService.getAllStockAssignment();
	}


	@GetMapping("/stock-assignment/{id}")
	public ResponseEntity<?> getStockAssignment(@PathVariable("id") Long id) {
		logger.info("Color Controller Getting Color By Id executing");
			return assignmentService.getStockAssignment(id);
	}



//	@PostMapping("/createStockAssignment")
//	public ResponseEntity<?> createStockAssignment(@RequestBody StockAssignmentRequest request) {
//			logger.info("Color Controller Saving New Color executing");
//			BaseResponse<StockAssignmentResponse> response = assignmentService.createStockAssignment(request);
//			logger.info("Color Service Saving New Color completed");
//			 logger.info("Color Controller Saving New Color completed");
//			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
//	}
	
	@PostMapping("/addInventory/{userId}")
	public ResponseEntity<?> addInventory(@PathVariable("userId") Long userId, @RequestBody StockAssignmentRequest request) {
			logger.info("Color Controller Saving New Color executing");
			return assignmentService.addInventory(userId, request);
			
	}
	
	
	@PostMapping("/saleProduct/{userId}")
	public ResponseEntity<?> saleProduct(@PathVariable("userId") Long userId, @RequestBody StockAssignmentRequest request) {
			logger.info("Color Controller Saving New Color executing");
			return assignmentService.saleProduct(userId, request);
	}
	
	
//	@PutMapping("/stock-assignment/{id}")
//	public ResponseEntity<?> updateStockAssignment(@PathVariable("id") Long id, @RequestBody StockAssignmentRequest request) {
//			return assignmentService.updateStockAssignment(id, request);
//	}


	@DeleteMapping("/stock-assignment/{id}")
	public ResponseEntity<?> deleteStockAssignment(@PathVariable("id") Long id) {
		logger.info("Color Controller Deleting Existing Color executing");
		return assignmentService.deleteStockAssignment(id);
	}

	
}
