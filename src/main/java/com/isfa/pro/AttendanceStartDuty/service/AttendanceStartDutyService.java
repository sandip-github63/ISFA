package com.isfa.pro.AttendanceStartDuty.service;

import org.springframework.stereotype.Service;

import com.isfa.pro.Attendance.entity.AttendanceEntity;
import com.isfa.pro.AttendanceStartDuty.entity.AttendanceStartDutyEntity;
import com.isfa.pro.AttendanceStartDuty.response.AttendanceStartDutyResponse;


@Service
public interface AttendanceStartDutyService {
	public AttendanceStartDutyResponse forDuty(AttendanceStartDutyEntity attendanceEntity);

}
