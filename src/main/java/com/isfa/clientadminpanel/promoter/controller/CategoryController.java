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

import com.isfa.clientadminpanel.promoter.model.CategoryRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.CategoryResponse;
import com.isfa.clientadminpanel.promoter.service.CategoryService;



@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@GetMapping("/category")
	public ResponseEntity<?> getAllCategories() {
		logger.info("Category Controller Getting All Category executing");
		return this.categoryService.getAllCategories();
	}


	@GetMapping("/getCategoryList/{userId}/{companyId}/{storeId}")
	public ResponseEntity<?> getCategoryById(@PathVariable("userId") Long userId, @PathVariable("companyId") Long companyId, @PathVariable("storeId") Long storeId) {
		logger.info("Category Controller Getting Category By Id executing");
			return categoryService.getCategoryById(userId, companyId, storeId);
	}


	
	@PostMapping("/category")
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
			logger.info("Category Controller Saving New Category executing");
			BaseResponse<CategoryResponse> response = categoryService.createCategory(request);
			logger.info("Category Service Saving New Category completed");
			 logger.info("Category Controller Saving New Category completed");
			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/category/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest request) {
		logger.info("Category Controller Deleting Existing Category executing");
		return categoryService.updateCategory(id, request);
	}


	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		logger.info("Category Controller Deleting Existing Category executing");
		return categoryService.deleteCategory(id);
	}

	
}
