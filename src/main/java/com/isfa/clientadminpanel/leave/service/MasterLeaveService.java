package com.isfa.clientadminpanel.leave.service;


import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.leave.model.MasterLeaveRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.MasterLeaveResponse;

public interface MasterLeaveService {
	
	ResponseEntity<?> findAllLeaves();
	ResponseEntity<?> findLeaveById(Long id);
	BaseResponse<MasterLeaveResponse> saveLeave(MasterLeaveRequest entity);
	ResponseEntity<?> updateLeave(Long id,MasterLeaveRequest request);
    ResponseEntity<?> deleteLeave(Long id);
}
