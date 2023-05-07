package com.isfa.clientadminpanel.leave.service;



import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.clientadminpanel.leave.model.LeaveTypeRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.LeaveTypeResponse;

public interface LeaveTypeService {
	
	ResponseEntity<?> getAllLeaves();
	ResponseEntity<?> getLeaveById(Long id)  throws IOException;
	BaseResponse<LeaveTypeResponse> createLeave(LeaveTypeRequest leaveTypeReq,MultipartFile image, Long userId) throws IOException;
	ResponseEntity<?> updateLeave(Long id,LeaveTypeRequest leave,MultipartFile image,Long userId) throws IOException;
	ResponseEntity<?> deleteLeave(Long id);
	
}
