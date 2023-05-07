package com.isfa.clientadminpanel.promoter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.clientadminpanel.promoter.model.StoreUserAssignmentRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.StoreUserAssignmentResponse;
import com.isfa.clientadminpanel.promoter.service.StoreUserAssignmentService;

@RestController
@RequestMapping("/api")
public class StoreUserAssignmentController {

	@Autowired
	private StoreUserAssignmentService storeUserAssignmentService;

	@GetMapping("/getAllStoreUserAssignment")
	public ResponseEntity<?> getAll() {
		return storeUserAssignmentService.getAllStoreUserAssignment();
	}

//	@GetMapping("/store-user-assignment/{id}")
//	public ResponseEntity<?> getById(@PathVariable Long id) {
//		return storeUserAssignmentService.getStoreUserAssignmentById(id);
//	}

	@PostMapping("/createStoreUserAssignment/{userId}")
	public ResponseEntity<?> create(@PathVariable("userId") Long userId, @RequestBody StoreUserAssignmentRequest request) {
		BaseResponse<StoreUserAssignmentResponse> response = storeUserAssignmentService
				.createStoreUserAssignment(userId,request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

//	@PutMapping("/updateStoreUserAssignment/{userId}/{assignedId}")
//	public ResponseEntity<?> update(@PathVariable("userId") Long userId, @PathVariable("assignedId") Long assignedId, @RequestBody StoreUserAssignmentRequest request) {
//		return storeUserAssignmentService.updateStoreUserAssignment(userId, assignedId, request);
//	}

	@DeleteMapping("/deleteStoreUserAssignment/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return storeUserAssignmentService.deleteStoreUserAssignment(id);
	}
}
