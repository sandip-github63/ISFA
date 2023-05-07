package com.isfa.leave.service;


import org.springframework.http.ResponseEntity;

import com.isfa.leave.model.LeaveRequestInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveRequestInfoResponse;

public interface LeaveRequestInfoService {
	
	ResponseEntity<?> getAllLeaveRequests(Long userId, Long companyId);
	ResponseEntity<?> getLeaveRequestById(Long id);
	BaseResponse<LeaveRequestInfoResponse> addLeaveRequest(LeaveRequestInfoRequest leaveRequest);
	ResponseEntity<?> deleteLeaveRequest(Long id);
	ResponseEntity<?> updateLeaveRequest(Long id, LeaveRequestInfoRequest leaveRequest);
//	ResponseEntity<?> acceptOrReject(LeaveRequestInfoRequest leaveRequest);

}
