package com.isfa.promoter.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.promoter.dao.StoreProductMappingRepository;
import com.isfa.promoter.entities.StoreProductMapping;
import com.isfa.promoter.model.StoreProductMappingRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StoreProductMappingResponse;

@Service
public class StoreProductMappingServiceImpl implements StoreProductMappingService{

	@Autowired
	StoreProductMappingRepository repository;
	
	@Override
	public BaseResponse<StoreProductMappingResponse> createStoreProduct(StoreProductMappingRequest request) {

		LocalDateTime now = LocalDateTime.now();
		request.setCreatedDate(now);
		request.setModifiedDate(now);
		
		StoreProductMapping obj = request.convertInto();
		obj = repository.save(obj);
		
		BaseResponse<StoreProductMappingResponse> bResp = new BaseResponse<>();
		bResp.setMessage("Record successfully Inserted");
		bResp.setStatus("200");
		
		return bResp;
	}
	
	@Override
	public ResponseEntity<?> updateStoreProduct(Long storeProductId, StoreProductMappingRequest request) {

		Optional<StoreProductMapping> existMapping = repository.findById(storeProductId);
		
		if(existMapping.isPresent()) {
			request.setStoreProductId(storeProductId);
			BaseResponse<StoreProductMappingResponse> bResp = createStoreProduct(request);
			
			bResp.setMessage("Record successfully updated");
			bResp.setStatus("200");
			
			return new ResponseEntity<>(bResp,HttpStatus.OK);
		}
		
		BaseResponse<StoreProductMappingResponse> bResp = new BaseResponse<>();
		bResp.setMessage("Record does not exists");
		bResp.setStatus("404");
		
		return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<?> deleteStoreProduct(Long storeProductId) {

		Optional<StoreProductMapping> existMapping = repository.findById(storeProductId);
		BaseResponse<StoreProductMappingResponse> bResp = new BaseResponse<>();
		
		if(existMapping.isPresent()) {
			repository.deleteById(storeProductId);
			bResp.setMessage("Record successfully deleted");
			bResp.setStatus("200");
			return new ResponseEntity<>(bResp,HttpStatus.OK);
		}
		
		bResp.setMessage("Record does not exists");
		bResp.setStatus("404");
		return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
	}
}
