package com.isfa.leave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.leave.model.LeaveBalanceInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveBalanceInfoResponse;
import com.isfa.leave.service.LeaveBalanceInfoService;


@RestController
@RequestMapping("/api")
public class LeaveBalanceInfoController {
	
	
		private static final Logger logger = LoggerFactory.getLogger(LeaveBalanceInfoController.class);
		
		@Autowired
		private LeaveBalanceInfoService myService;
		
		@GetMapping("/leave_balance")
		public ResponseEntity<?> getAllLeaveBalance() {
			logger.info("LeaveBalanceInfo Controller Getting All LeaveBalanceInfo executing");
			return this.myService.findAllLeaveBalance();
		}
		
		
		@GetMapping("/leave_balance/{id}")
		public ResponseEntity<?> getLeaveBalanceById(@PathVariable("id") Long id) {
			logger.info("LeaveBalanceInfo Controller Getting LeaveBalanceInfo By Id executing");
			return myService.findByLeaveBalanceId(id);
		}
		
		@PostMapping("/leave_balance")
		public ResponseEntity<?> saveLeaveBalance(@RequestBody LeaveBalanceInfoRequest request) {
			logger.info("LeaveBalanceInfo Controller Saving New LeaveBalanceInfo executing");
			BaseResponse<LeaveBalanceInfoResponse> response = this.myService.saveLeaveBalance(request);
			logger.info("LeaveBalanceInfo Controller Saving New LeaveBalanceInfo completed");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		
		@PutMapping("/leave_balance/{id}")
		public ResponseEntity<?> updateLeaveBalance(@PathVariable("id") Long id, @RequestBody LeaveBalanceInfoRequest request) {
			logger.info("LeaveBalanceInfo Controller Deleting Existing LeaveBalanceInfo executing");
			return myService.updateLeaveBalance(id,request);
		}
		
		@DeleteMapping("/leave_balance/{id}")
		public ResponseEntity<?> deleteLeaveBalance(@PathVariable("id") Long id) {
			logger.info("LeaveBalanceInfo Controller Deleting Existing LeaveBalanceInfo executing");
			return myService.deleteLeaveBalanceById(id);
		}

}

