/*package com.isfa.clientadminpanel.promoter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.clientadminpanel.promoter.model.CategoryRequest;
import com.isfa.clientadminpanel.promoter.model.ProductRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.CategoryResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;
import com.isfa.clientadminpanel.promoter.service.CategoryService;
import com.isfa.clientadminpanel.promoter.service.ProductService;


@RestController
@RequestMapping("/api")
public class PromoterMasterController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping("/category")
	public ResponseEntity<?> categoryController(@RequestBody CategoryRequest request ){
		String task = request.getTask();
		Long id = request.getCategoryId();
		
		if("get".equalsIgnoreCase(task)){
			return categoryService.getCategoryById(id);
		}
		else if("create".equalsIgnoreCase(task)) {
			BaseResponse<CategoryResponse> response = categoryService.createCategory(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else if("update".equalsIgnoreCase(task)) {
			return categoryService.updateCategory(id, request);
		}
		else if("delete".equalsIgnoreCase(task)) {
			return categoryService.deleteCategory(id);
		}
		return categoryService.getAllCategories();
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<?> productController(@RequestBody ProductRequest request ){
		String task = request.getTask();
		Long id = request.getProductId();
		
		if("get".equalsIgnoreCase(task)){
			return productService.getProductById(id);
		}
		else if("create".equalsIgnoreCase(task)) {
			BaseResponse<ProductResponse> response = productService.createProduct(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else if("update".equalsIgnoreCase(task)) {
			return productService.updateProduct(id, request);
		}
		else if("delete".equalsIgnoreCase(task)) {
			return productService.deleteProduct(id);
		}
		return productService.getAllProducts();
	}
	
	
	
}
*/
