package com.isfa.leave.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GetEmpLeaveDetailService {

	public ResponseEntity<?> getEmpLeaveDetails(Long userId, Long companyId);
	
}
