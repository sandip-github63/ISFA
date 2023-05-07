/*package com.isfa.clientadminpanel.leave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.clientadminpanel.leave.model.ColorRequest;
import com.isfa.clientadminpanel.leave.model.IconRequest;
import com.isfa.clientadminpanel.leave.model.LeaveTypeRequest;
import com.isfa.clientadminpanel.leave.model.MasterLeaveRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.ColorResponse;
import com.isfa.clientadminpanel.leave.response.IconResponse;
import com.isfa.clientadminpanel.leave.response.LeaveTypeResponse;
import com.isfa.clientadminpanel.leave.response.MasterLeaveResponse;
import com.isfa.clientadminpanel.leave.service.ColorService;
import com.isfa.clientadminpanel.leave.service.IconService;
import com.isfa.clientadminpanel.leave.service.LeaveTypeService;
import com.isfa.clientadminpanel.leave.service.MasterLeaveService;

@RestController
@RequestMapping("/api")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private ColorService colorService;
	@Autowired
    private IconService iconService;
	@Autowired
	private LeaveTypeService leaveService;
	@Autowired
    private MasterLeaveService masterLeaveService;
	
	@PostMapping("/colors")
	public ResponseEntity<?> colorController(@RequestBody ColorRequest tColor){
		
		String task = tColor.getTask();
		Long id = tColor.getId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("Color Controller Saving New Color executing");
			BaseResponse<ColorResponse> response = this.colorService.saveTmColor(tColor);
			logger.info("Color Controller Saving New Color completed");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else if("get".equalsIgnoreCase(task)) {
			logger.info("Color Controller Getting Color By Id executing");
			return colorService.getTmColorById(id);
		}
		else if("update".equalsIgnoreCase(task)) {
			logger.info("Color Controller Updating existing Color executing");
			return colorService.updateTmColor(id,tColor);
		}
		else if("delete".equalsIgnoreCase(task)) {
			logger.info("Color Controller Deleting Existing Color executing");
			return colorService.deleteTmColorById(id);
		}
		else {
			logger.info("Color Controller Getting All Colors executing");
			return this.colorService.getAllTmColors();
		}	
		
	}
	
	@PostMapping("/icons")
	public ResponseEntity<?> iconController(@RequestBody IconRequest iconRequest){
		
		String task = iconRequest.getTask();
		Long iconId = iconRequest.getIconId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("Icon Controller Saving New Icon executing");
			BaseResponse<IconResponse> createdIcon = this.iconService.createIcon(iconRequest);
   		 	logger.info("Icon Controller Saving New Icon completed");
    		return ResponseEntity.status(HttpStatus.CREATED).body(createdIcon);
		}
		else if("get".equalsIgnoreCase(task)) {
			logger.info("Icon Controller Getting Icon By Id executing");
	    	return iconService.getIconById(iconId);
		}
		else if("update".equalsIgnoreCase(task)) {
			logger.info("Icon Controller Updating Existing Icon executing");
	    	return  iconService.updateIcon(iconId,iconRequest);
		}
		else if("delete".equalsIgnoreCase(task)) {
			return this.iconService.deleteIcon(iconId);
		}
		else {
			logger.info("Icon Controller Getting All icons executing");
			return iconService.getAllIcons();
		}	
		
	}
	
	
	@PostMapping("/leave")
	public ResponseEntity<?> leaveController(@RequestBody LeaveTypeRequest leaveRequest){
		
		String task = leaveRequest.getTask();
		Integer id = leaveRequest.getLeaveId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("LeaveType Controller Saving New Leave executing");
			BaseResponse<LeaveTypeResponse> leaveType = leaveService.createLeave(leaveRequest);
			logger.info("LeaveType Controller Saving New Leave completed");
	        return ResponseEntity.status(HttpStatus.CREATED).body(leaveType);
		}
		else if("get".equalsIgnoreCase(task)) {
			logger.info("Leave Type Controller Getting Leave By Id executing");
	    	return leaveService.getLeaveById(id);
		}
		else if("update".equalsIgnoreCase(task)) {
			return leaveService.updateLeave(id,leaveRequest);
		}
		else if("delete".equalsIgnoreCase(task)) {
			logger.info("LeaveType Controller Deleting Existing Leave executing");
	    	return leaveService.deleteLeave(id);
		}
		else {
			logger.info("LeaveType Controller Getting All Leaves executing");
	        return leaveService.getAllLeaves();
		}	
		
	}
	
	
	
	@PostMapping("/master_leave")
	public ResponseEntity<?> masterLeaveController(@RequestBody MasterLeaveRequest request){
		
		String task = request.getTask();
		Long id = request.getDayId();
		
		if("create".equalsIgnoreCase(task)) {
			logger.info("MasterLeave Controller Saving New Leave executing");
			BaseResponse<MasterLeaveResponse> savedResponse = masterLeaveService.saveLeave(request);
		 	logger.info("MasterLeave Controller Saving New Leave completed");
	    	return ResponseEntity.status(HttpStatus.CREATED).body(savedResponse);
		}
		else if("get".equalsIgnoreCase(task)) {
			logger.info("MasterLeave Controller Getting Leave By Id executing");
	        return masterLeaveService.findLeaveById(id);
		}
		else if("update".equalsIgnoreCase(task)) {
			logger.info("MasterLeave Controller Updating Existing Leave executing");
	    	return masterLeaveService.updateLeave(id,request);
		}
		else if("delete".equalsIgnoreCase(task)) {
			logger.info("MasterLeave Controller Deleting Existing Leave executing");
	    	return masterLeaveService.deleteLeave(id);
		}
		else {
			logger.info("MasterLeave Controller Getting All Leaves executing");
	        return masterLeaveService.findAllLeaves();
		}	
		
	}
	
}
*/
