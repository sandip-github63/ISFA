package com.isfa.clientadminpanel.promoter.controller;

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

import com.isfa.clientadminpanel.promoter.model.ProductRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;
import com.isfa.clientadminpanel.promoter.service.ProductDetailService;
import com.isfa.clientadminpanel.promoter.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDetailService productDetailService;
	
private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/getProductList/{storeId}")
	public ResponseEntity<?> getAllProducts(@PathVariable("storeId") Long storeId) {
		logger.info("Product Controller Getting All Product executing");
		return this.productService.getAllProducts(storeId);
	}

	@GetMapping("/getInventory/{userId}/{companyId}/{storeId}")
	public ResponseEntity<?> getInventory(@PathVariable("userId") Long userId, @PathVariable("companyId") Long companyId, @PathVariable("storeId") Long storeId) {
		logger.info("Product Controller Getting All Product executing");
		return this.productDetailService.getInventory(userId, companyId, storeId);
	}


	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
		logger.info("Product Controller Getting Product By Id executing");
			return productService.getProductById(id);
	}


	
	@PostMapping("/addProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
			logger.info("Product Controller Saving New Product executing");
			BaseResponse<ProductResponse> response = productService.createProduct(request);
			logger.info("Product Service Saving New Product completed");
			 logger.info("Product Controller Saving New Product completed");
			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
		logger.info("Product Controller Updating existing Product executing");
		return productService.updateProduct(id, request);
	}


	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		logger.info("Product Controller Deleting Existing Product executing");
		return productService.deleteProduct(id);
	}

	
	
}
