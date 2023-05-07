package com.isfa.clientadminpanel.leave.controller;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.clientadminpanel.leave.model.LeaveTypeRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.LeaveTypeResponse;
import com.isfa.clientadminpanel.leave.service.LeaveTypeService;


@RestController
@RequestMapping("/api")
public class LeaveTypeController {

private static final Logger logger = LoggerFactory.getLogger(LeaveTypeController.class);
	
    @Autowired
    private LeaveTypeService myService;
    
    @GetMapping("/leave")
    public ResponseEntity<?> getAllLeaves() {
    	logger.info("LeaveType Controller Getting All Leaves executing");
        return myService.getAllLeaves();	
    }
    
    @GetMapping("/getLeaveType/{id}")
    public ResponseEntity<?> getLeaveById(@PathVariable("id") Long id) throws IOException {
    	logger.info("Leave Type Controller Getting Leave By Id executing");
    	return myService.getLeaveById(id);
    }
    
//    @PostMapping("/leave")
//    public ResponseEntity<?> createLeave(@RequestBody LeaveTypeRequest leave) {
//    	logger.info("LeaveType Controller Saving New Leave executing");
//    	BaseResponse<LeaveTypeResponse> leaveType = myService.createLeave(leave);
//    	logger.info("LeaveType Service Saving New Leave completed");
//		 	logger.info("LeaveType Controller Saving New Leave completed");
//        return ResponseEntity.status(HttpStatus.CREATED).body(leaveType);
//    }
    
    @PostMapping("/createLeaveType/{userId}")
    public ResponseEntity<?> createLeave(@ModelAttribute LeaveTypeRequest leave, @RequestParam(value = "image", required = false) MultipartFile image, @PathVariable("userId") Long userId)	throws IOException{
    	logger.info("LeaveType Controller Saving New Leave executing");
    	BaseResponse<LeaveTypeResponse> leaveType = myService.createLeave(leave,image,userId);
    	logger.info("LeaveType Service Saving New Leave completed");
		logger.info("LeaveType Controller Saving New Leave completed");
        return ResponseEntity.status(HttpStatus.OK).body(leaveType);
    }

//    @PostMapping(value="/createLeaveType/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> createLeave(MultipartHttpServletRequest request, @PathVariable("userId") Long userId)	throws IOException{
//    	logger.info("LeaveType Controller Saving New Leave executing");
//    	
//
//    	// Extract the uploaded image from the request
//    	MultipartFile image = request.getFile("image");
//    	
//    	// Convert the other form data to a LeaveTypeRequest object
//    	String leaveType = request.getParameter("leaveType");
//    	String leaveDesc = request.getParameter("leaveDesc");
//    	String specialLeaveStr = request.getParameter("specialLeave");
//    	Boolean specialLeave = Boolean.parseBoolean(specialLeaveStr);
//    	String color = request.getParameter("color");
//    	String icon = request.getParameter("icon");
//    	String activeStr = request.getParameter("active");
//    	Boolean active = Boolean.parseBoolean(activeStr);
//    	
//    	LeaveTypeRequest leave = new LeaveTypeRequest();
//    	leave.setLeaveType(leaveType);
//    	leave.setLeaveDesc(leaveDesc);
//    	leave.setSpecialLeave(specialLeave);
//    	leave.setColor(color);
//    	leave.setIcon(icon);
//    	leave.setActive(active);
//    	
//    	BaseResponse<LeaveTypeResponse> leaveResp = myService.createLeave(leave,image,userId);
//    	logger.info("LeaveType Service Saving New Leave completed");
//    	logger.info("LeaveType Controller Saving New Leave completed");
//    	return ResponseEntity.status(HttpStatus.CREATED).body(leaveResp);
//    }
    
    
//    @PutMapping("/leave/{id}")
//    public ResponseEntity<?> updateLeave(@PathVariable Long id, @RequestBody LeaveTypeRequest leave) {
//    	logger.info("LeaveType Controller Updating Existing Leave executing");
//    	return myService.updateLeave(id,leave);
//    }
    
    @PutMapping("/updateLeaveType/{id}/{userId}")
    public ResponseEntity<?> updateLeave(@PathVariable("id") Long id, @ModelAttribute LeaveTypeRequest leave, @RequestParam(value = "image", required = false) MultipartFile image, @PathVariable("userId") Long userId)	throws IOException{
    	logger.info("LeaveType Controller Updating Existing Leave executing");
    	return myService.updateLeave(id,leave,image,userId);
    }
    
    
    @DeleteMapping("/leave/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    	logger.info("LeaveType Controller Deleting Existing Leave executing");
    	return myService.deleteLeave(id);
    }
	
}

