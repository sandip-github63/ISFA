/*package com.isfa.leave.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.leave.model.LeaveBalanceInfoRequest;
import com.isfa.leave.model.LeaveRequestInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveBalanceInfoResponse;
import com.isfa.leave.response.LeaveRequestInfoResponse;
import com.isfa.leave.service.LeaveBalanceInfoService;
import com.isfa.leave.service.LeaveRequestInfoService;

@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private LeaveRequestInfoService requestInfoService;
	@Autowired
	private LeaveBalanceInfoService balanceInfoService;
	
	
	@PostMapping("/request")
	public ResponseEntity<?> leaveRequestInfoController(@RequestBody LeaveRequestInfoRequest request){
		
		String task = request.getTask();
		Long id = request.getLeaveRequestId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("LeaveRequestInfo Controller Saving New LeaveRequest executing");
			BaseResponse<LeaveRequestInfoResponse> info = requestInfoService.addLeaveRequest(request);
			logger.info("LeaveRequestInfo Controller Saving New LeaveRequest completed");
			return ResponseEntity.status(HttpStatus.CREATED).body(info);
		}
		if("get".equalsIgnoreCase(task)) {
			logger.info("LeaveRequestInfo Controller Getting LeaveRequest By Id executing");
			return requestInfoService.getLeaveRequestById(id);
		}
		if("update".equalsIgnoreCase(task)) {
			logger.info("LeaveRequestInfo Controller Updating existing LeaveRequest executing");
			return requestInfoService.updateLeaveRequest(id, request);
		}
		if("delete".equalsIgnoreCase(task)) {
			logger.info("LeaveRequestInfo Controller Deleting Existing LeaveRequest executing");
			return requestInfoService.deleteLeaveRequest(id);
		}
		else{
			 logger.info("LeaveRequestInfo Controller Getting All LeaveRequest executing");
			 return requestInfoService.getAllLeaveRequests();
		}
		
	}
	
	
	@PostMapping("/leave_balance")
	public ResponseEntity<?> leaveBalanceInfoController(@RequestBody LeaveBalanceInfoRequest request){
		
		String task = request.getTask();
		Long id = request.getLeaveBalanceId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("LeaveBalanceInfo Controller Saving New LeaveBalanceInfo executing");
			BaseResponse<LeaveBalanceInfoResponse> response = this.balanceInfoService.saveLeaveBalance(request);
			logger.info("LeaveBalanceInfo Controller Saving New LeaveBalanceInfo completed");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		if("get".equalsIgnoreCase(task)) {
			logger.info("LeaveBalanceInfo Controller Getting LeaveBalanceInfo By Id executing");
			return balanceInfoService.findByLeaveBalanceId(id);
		}
		if("update".equalsIgnoreCase(task)) {
			logger.info("LeaveBalanceInfo Controller Updating Existing LeaveBalanceInfo executing");
			return balanceInfoService.updateLeaveBalance(id,request);
		}
		if("delete".equalsIgnoreCase(task)) {
			logger.info("LeaveBalanceInfo Controller Deleting Existing LeaveBalanceInfo executing");
			return balanceInfoService.deleteLeaveBalanceById(id);
		}
		else{
			logger.info("LeaveBalanceInfo Controller Getting All LeaveBalanceInfo executing");
			return this.balanceInfoService.findAllLeaveBalance();
		}
		
	}
}
*/
