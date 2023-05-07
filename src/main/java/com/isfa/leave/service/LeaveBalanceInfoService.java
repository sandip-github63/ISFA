package com.isfa.leave.service;

import org.springframework.http.ResponseEntity;

import com.isfa.leave.model.LeaveBalanceInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveBalanceInfoResponse;


public interface LeaveBalanceInfoService {
	
    ResponseEntity<?> findAllLeaveBalance();

    ResponseEntity<?> findByLeaveBalanceId(Long id);

    BaseResponse<LeaveBalanceInfoResponse> saveLeaveBalance(LeaveBalanceInfoRequest leaveBalanceInfoRequest);
    
    ResponseEntity<?> updateLeaveBalance(Long id, LeaveBalanceInfoRequest leaveBalanceInfoRequest);
    
    ResponseEntity<?> deleteLeaveBalanceById(Long id);
	
}
