package com.isfa.leave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.leave.service.GetEmpLeaveDetailService;

@RestController
@RequestMapping("/api")
public class GetEmpLeaveDetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(LeaveBalanceInfoController.class);
	
	@Autowired
	GetEmpLeaveDetailService getEmpLeaveDetailService;
	
	@GetMapping("/getEmpLeaveDetails/{userId}/{companyId}")
	public ResponseEntity<?> getEmpLeaveDetails(@PathVariable Long userId,@PathVariable Long companyId){
		logger.info("GetEmpLeaveDetailService Controller getEmpLeaveDetails() executing");
		return getEmpLeaveDetailService.getEmpLeaveDetails(userId,companyId);
		
	}
	
	
}