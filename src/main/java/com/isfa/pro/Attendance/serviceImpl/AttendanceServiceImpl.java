package com.isfa.pro.Attendance.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isfa.pro.Attendance.dao.AttendanceRepo;
import com.isfa.pro.Attendance.entity.AttendanceEntity;
import com.isfa.pro.Attendance.response.AttendanceResponse;
import com.isfa.pro.Attendance.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	AttendanceRepo repo;

	@Override
	public AttendanceResponse markInOut(AttendanceEntity attendanceEntity) {
		AttendanceResponse response=new AttendanceResponse();
		try {
			if (attendanceEntity != null && attendanceEntity.getStatus().equals("true"))

			{
				AttendanceEntity resObj = this.repo.save(attendanceEntity);
				response.setMessage("MarkIn Attendance Successfuly login !!");
				response.setStatusCode(200);
				return response;
			} else {
				AttendanceEntity entity = this.repo.findByUserIdAndStoreId(attendanceEntity.getUserId(),
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
				response.setMessage("MarkOut Attendance Successfuly logout !!");
				response.setStatusCode(200);
				return response;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setMessage("object is null !!");
		response.setStatusCode(402);
		return response;
	}
}
