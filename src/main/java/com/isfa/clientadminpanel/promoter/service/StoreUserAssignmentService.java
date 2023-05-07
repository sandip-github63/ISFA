package com.isfa.clientadminpanel.promoter.service;

import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.promoter.model.StoreUserAssignmentRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.StoreUserAssignmentResponse;

public interface StoreUserAssignmentService {
	ResponseEntity<?> getAllStoreUserAssignment();

//	ResponseEntity<?> getStoreUserAssignmentById(Long id);

	BaseResponse<StoreUserAssignmentResponse> createStoreUserAssignment(Long userId, StoreUserAssignmentRequest request);

	ResponseEntity<?> updateStoreUserAssignment(Long userId, Long id, StoreUserAssignmentRequest request);

	ResponseEntity<?> deleteStoreUserAssignment(Long id);
}
