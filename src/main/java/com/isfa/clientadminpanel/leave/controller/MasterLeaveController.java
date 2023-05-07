package com.isfa.clientadminpanel.leave.controller;


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

import com.isfa.clientadminpanel.leave.model.MasterLeaveRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.MasterLeaveResponse;
import com.isfa.clientadminpanel.leave.service.MasterLeaveService;

@RestController
@RequestMapping("/api")
public class MasterLeaveController {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterLeaveController.class);
	
	@Autowired
    private MasterLeaveService myService;

    @GetMapping("/master_leave")
    public ResponseEntity<?> findAll() {
    	logger.info("MasterLeave Controller Getting All Leaves executing");
        return myService.findAllLeaves();
    }

    @GetMapping("/master_leave/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    	logger.info("MasterLeave Controller Getting Leave By Id executing");
        return myService.findLeaveById(id);
    }

    @PostMapping("/master_leave")
    public ResponseEntity<?> create(@RequestBody MasterLeaveRequest request) {
    	logger.info("MasterLeave Controller Saving New Leave executing");
    	BaseResponse<MasterLeaveResponse> savedResponse = myService.saveLeave(request);
    	logger.info("MasterLeave Service Saving New Leave completed");
	 	logger.info("MasterLeave Controller Saving New Leave completed");
    	return ResponseEntity.status(HttpStatus.CREATED).body(savedResponse);
    }

    @PutMapping("/master_leave/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MasterLeaveRequest request){      
    	logger.info("MasterLeave Controller Updating Existing Leave executing");
    	return myService.updateLeave(id,request);
}

    @DeleteMapping("/master_leave/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    	logger.info("MasterLeave Controller Deleting Existing Leave executing");
    	return myService.deleteLeave(id);
    }
}

