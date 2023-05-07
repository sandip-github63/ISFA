package com.isfa.promoter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.promoter.model.StoreProductMappingRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StoreProductMappingResponse;
import com.isfa.promoter.service.StoreProductMappingService;



@RestController
@RequestMapping("/api")
public class StoreProductMappingController {

	@Autowired
	StoreProductMappingService mappingService;
	
	
private static final Logger logger = LoggerFactory.getLogger(StoreProductMappingController.class);
	
//	@GetMapping("/product")
//	public ResponseEntity<?> getAllProducts() {
//		logger.info("Color Controller Getting All Colors executing");
//		return this.mappingService.
//	}


//	@GetMapping("/product/{id}")
//	public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
//		logger.info("Color Controller Getting Color By Id executing");
//			return mappingService.getProductById(id);
//	}
//


	@PostMapping("/store-product")
	public ResponseEntity<?> createStoreProduct(@RequestBody StoreProductMappingRequest request) {
			logger.info("StoreProductMapping Controller Saving New StoreProductMapping executing");
			BaseResponse<StoreProductMappingResponse> response = mappingService.createStoreProduct(request);
			logger.info("StoreProductMapping Service Saving New StoreProductMapping completed");
			 logger.info("StoreProductMapping Controller Saving New StoreProductMapping completed");
			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/store-product/{id}")
	public ResponseEntity<?> updateStoreProduct(@PathVariable("id") Long id, @RequestBody StoreProductMappingRequest request) {
		logger.info("StoreProductMapping Controller Updating Existing StoreProductMapping executing");
			return mappingService.updateStoreProduct(id, request);
	}


	@DeleteMapping("/store-product/{id}")
	public ResponseEntity<?> deleteStoreProduct(@PathVariable("id") Long id) {
		logger.info("StoreProductMapping Controller Deleting Existing StoreProductMapping executing");
		return mappingService.deleteStoreProduct(id);
	}

	
}
