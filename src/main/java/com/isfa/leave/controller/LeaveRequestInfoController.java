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
import com.isfa.leave.model.LeaveRequestInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveRequestInfoResponse;
import com.isfa.leave.service.LeaveRequestInfoService;


@RestController
@RequestMapping("/api/leave")
public class LeaveRequestInfoController {

	private static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfoController.class);
	
    @Autowired
    private LeaveRequestInfoService leaveRequestService;

    @GetMapping("/getLeaveRequest/{userId}/{companyId}")
    public ResponseEntity<?> getAllLeaveRequests(@PathVariable Long userId,@PathVariable Long companyId) {
    	logger.info("LeaveRequestInfo Controller Getting All LeaveRequestInfo executing");
    	return leaveRequestService.getAllLeaveRequests(userId,companyId);

    }

    @GetMapping("/getLeaveRequest/{id}")
    public ResponseEntity<?> getLeaveRequestById(@PathVariable Long id) {
    	logger.info("LeaveRequestInfo Controller Getting LeaveRequestInfo By Id executing");
    	return leaveRequestService.getLeaveRequestById(id);

    }

    @PostMapping("/addLeaveRequest")
    public ResponseEntity<?> addLeaveRequest(@RequestBody LeaveRequestInfoRequest leaveRequest) {
    	logger.info("LeaveRequestInfo Controller Saving New LeaveRequestInfo executing");
    	BaseResponse<LeaveRequestInfoResponse> info = leaveRequestService.addLeaveRequest(leaveRequest);
    	logger.info("LeaveRequestInfo Controller Saving New LeaveRequestInfo completed");
    	return ResponseEntity.status(HttpStatus.CREATED).body(info);
    }

    
    @PutMapping("/updateLeaveRequest/{id}")
    public ResponseEntity<?> updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveRequestInfoRequest leaveRequest) {
    	logger.info("LeaveRequestInfo Controller updating Existing LeaveRequestInfo executing");
    	return leaveRequestService.updateLeaveRequest(id, leaveRequest);
    }

    @DeleteMapping("/deleteRequest/{id}")
    public ResponseEntity<?> deleteLeaveRequest(@PathVariable Long id) {
    	logger.info("LeaveRequestInfo Controller Deleting Existing LeaveRequestInfo executing");
    	return leaveRequestService.deleteLeaveRequest(id);
    }
}

