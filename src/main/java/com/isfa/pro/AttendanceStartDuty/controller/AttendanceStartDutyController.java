package com.isfa.pro.AttendanceStartDuty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.pro.AttendanceStartDuty.Request.AttendanceStartDutyRequest;
import com.isfa.pro.AttendanceStartDuty.entity.AttendanceStartDutyEntity;
import com.isfa.pro.AttendanceStartDuty.model.AttendanceStartDutyModel;
import com.isfa.pro.AttendanceStartDuty.response.AttendanceStartDutyResponse;
import com.isfa.pro.AttendanceStartDuty.service.AttendanceStartDutyService;

@RestController
public class AttendanceStartDutyController {
	@Autowired
	AttendanceStartDutyService service;
	AttendanceStartDutyResponse attendanceresponse = new AttendanceStartDutyResponse();
	AttendanceStartDutyModel model;

	@GetMapping(value = { "/duty" })
	public String home() {
		return "home page";
	}

	@PostMapping({"/StartDuty","/EndDuty"})
	public ResponseEntity<?> startDuty(
			@RequestBody AttendanceStartDutyRequest request/* ,@RequestParam("file") MultipartFile image */) {
		attendanceresponse.setMessage("StartDuty Attendance Successfuly login !!");
		attendanceresponse.setStatusCode(200);
		model = new AttendanceStartDutyModel();
		String imageName = "webapp/photo/";
		if(!request.equals(null)) {
		AttendanceStartDutyEntity attendanceEntity = model.getAttendanceEntity(request, imageName);
		attendanceresponse = this.service.forDuty(attendanceEntity);
		return new ResponseEntity<>(attendanceresponse, HttpStatus.OK);
		}
		else {
			attendanceresponse.setMessage("Object is null");
			attendanceresponse.setStatusCode(402);
			return new ResponseEntity<>(attendanceresponse, HttpStatus.BAD_REQUEST);
		}
	}
	}

