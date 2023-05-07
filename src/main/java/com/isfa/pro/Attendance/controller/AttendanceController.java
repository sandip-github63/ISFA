package com.isfa.pro.Attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.pro.Attendance.Request.AttendanceRequest;
import com.isfa.pro.Attendance.entity.AttendanceEntity;
import com.isfa.pro.Attendance.model.AttendanceModel;
import com.isfa.pro.Attendance.response.AttendanceResponse;
import com.isfa.pro.Attendance.service.AttendanceService;

@RestController
public class AttendanceController {
	@Autowired
	AttendanceService service;
	AttendanceResponse attendanceresponse = new AttendanceResponse();
	AttendanceModel model;

	@GetMapping(value = { "/V1/home", "/V2/login" })
	public String home() {
		return "home page";
	}

	@PostMapping({"/MarkIn","/MarkOut"})
	public ResponseEntity<?> markInOut(@RequestBody AttendanceRequest request/*,@RequestParam("file") MultipartFile image*/) {
		attendanceresponse.setMessage("MarkIn Successfully !!");
		attendanceresponse.setStatusCode(200);
		model = new AttendanceModel();
		String imageName="image";
		if(!request.equals(null)) {	
		AttendanceEntity attendanceEntity = model.getAttendanceEntity(request,imageName);
		attendanceresponse = this.service.markInOut(attendanceEntity);
		return new ResponseEntity<>(attendanceresponse,HttpStatus.OK);
		}else {
			attendanceresponse.setMessage("Object is null");
			attendanceresponse.setStatusCode(402);
			return new ResponseEntity<>(attendanceresponse,HttpStatus.BAD_REQUEST);	
		}
	}
	@PostMapping("/data")
	public String InsertData(@RequestBody String mess) {
		return mess;
	}

}
