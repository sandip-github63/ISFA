package com.isfa.pro.AttendanceStartDuty.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isfa.pro.Attendance.dao.AttendanceRepo;
import com.isfa.pro.Attendance.service.AttendanceService;
import com.isfa.pro.AttendanceStartDuty.dao.AttendanAttendanceStartDutyRepo;
import com.isfa.pro.AttendanceStartDuty.entity.AttendanceStartDutyEntity;
import com.isfa.pro.AttendanceStartDuty.response.AttendanceStartDutyResponse;
import com.isfa.pro.AttendanceStartDuty.service.AttendanceStartDutyService;

@Service
public class AttendanceStartDutyServiceImpl implements AttendanceStartDutyService {
	@Autowired
	AttendanAttendanceStartDutyRepo repo;

	@Override
	public AttendanceStartDutyResponse forDuty(AttendanceStartDutyEntity attendanceEntity) {
		AttendanceStartDutyResponse response=new AttendanceStartDutyResponse();
		try {
			if (attendanceEntity != null && attendanceEntity.getStatus().equals("true"))

			{
				AttendanceStartDutyEntity resObj = this.repo.save(attendanceEntity);
				response.setMessage("StartDuty Attendance Successfuly login !!");
				response.setStatusCode(200);
				return response;
			} else {
				AttendanceStartDutyEntity entity = this.repo.findByUserIdAndStoreId(attendanceEntity.getUserId(),
						attendanceEntity.getStoreId());
				entity.setOutStatus("true");
				entity.setCurrentStatus("Deactive");
				entity.setIsOffline("true");
				entity.setMarkoutOffline(attendanceEntity.getMarkoutOffline());
				entity.setOutLatitude(attendanceEntity.getOutLatitude());
				entity.setOutLongitude(attendanceEntity.getOutLongitude());
				entity.setOutLocation(attendanceEntity.getOutLocation());
				entity.setDateModified(attendanceEntity.getDateModified());
				entity.setHealthStatus(attendanceEntity.getHealthStatus());
				entity.setMaskValidation(attendanceEntity.getMaskValidation());
				// Mark out image code
				// attendanceEntity.setOutImage(req.getOutImage());
				entity.setOutImage("/webapp/image/MarkOut/Photos");
				entity.setInOrOut(attendanceEntity.getInOrOut());
				entity.setOutDistance(attendanceEntity.getOutDistance());
				entity.setOutDate(attendanceEntity.getOutDate());
				entity.setOutTime(attendanceEntity.getOutTime());
				this.repo.save(entity);
				response.setMessage("Attendance Successfuly logout !!");
				response.setStatusCode(200);
				return response;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setMessage("Object is Null !!");
		response.setStatusCode(402);
		return response;
	}
}
