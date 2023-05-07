package com.isfa.clientadminpanel.promoter.service;


import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.promoter.model.ProductRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;

public interface ProductService {

    ResponseEntity<?> getAllProducts(Long storeId);

    ResponseEntity<?> getProductById(Long productId);

    BaseResponse<ProductResponse> createProduct(ProductRequest request);

    ResponseEntity<?> updateProduct(Long productId, ProductRequest request);

    ResponseEntity<?> deleteProduct(Long productId);
}
