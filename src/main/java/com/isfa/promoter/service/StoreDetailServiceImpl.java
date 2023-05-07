package com.isfa.promoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.promoter.dao.StoreUserAssignmentRepository;
import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;
import com.isfa.promoter.dao.StoreDetailRepository;
import com.isfa.promoter.entities.StoreDetail;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StoreDetailResponse;

@Service
public class StoreDetailServiceImpl implements StoreDetailService{

	@Autowired
	StoreUserAssignmentRepository storeUserRepository;
	
	@Autowired
	StoreDetailRepository detailRepository;
	
	public ResponseEntity<?> getPromoterDetail(Long userId, Long companyId){
		
		StoreUserAssignment assign = storeUserRepository.findByUserIdAndCampaignId(userId, companyId).orElse(null);
		System.out.println(assign);
		
		if(assign!=null) {
			StoreDetail detail = detailRepository.findByStoreId(assign.getStoreId()).orElse(null);
			System.out.println(detail);
			
			if(detail!=null) {
				BaseResponse<StoreDetailResponse> response = StoreDetailResponse.convert(detail);
				response.setStatus("200");
				response.setMessage("Record Successfully Fetched");
				
				return new ResponseEntity<>(response,HttpStatus.OK);
			}else {
				BaseResponse<StoreDetailResponse> response = new BaseResponse<>();
				response.setStatus("200");
				response.setMessage("No Store available on the provided details");
				
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			
		}else {
			BaseResponse<StoreDetailResponse> response = new BaseResponse<>();
			response.setStatus("200");
			response.setMessage("No Store available on the provided details");
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
	}

	
}
