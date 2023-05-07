package com.isfa.pro.Attendance.service;

import org.springframework.stereotype.Service;

import com.isfa.pro.Attendance.entity.AttendanceEntity;
import com.isfa.pro.Attendance.response.AttendanceResponse;


@Service
public interface AttendanceService {
	public AttendanceResponse markInOut(AttendanceEntity attendanceEntity);

}
