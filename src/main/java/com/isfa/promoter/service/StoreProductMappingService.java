package com.isfa.promoter.service;

import org.springframework.http.ResponseEntity;

import com.isfa.promoter.model.StoreProductMappingRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StoreProductMappingResponse;

public interface StoreProductMappingService {

//    ResponseEntity<?> getAllStoreProducts();

//	ResponseEntity<?> getStoreProductById(Long storeProductId);

    BaseResponse<StoreProductMappingResponse> createStoreProduct(StoreProductMappingRequest request);

    ResponseEntity<?> updateStoreProduct(Long storeProductId, StoreProductMappingRequest request);

    ResponseEntity<?> deleteStoreProduct(Long storeProductId);
}

