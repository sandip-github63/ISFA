package com.isfa.pro.Attendance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

import com.isfa.pro.Attendance.Request.AttendanceRequest;
import com.isfa.pro.Attendance.entity.AttendanceEntity;

import lombok.Data;

@Data
public class AttendanceModel {
	public String status = "false";
	public String isoffline = "false";

	private AttendanceEntity forMarkIn(AttendanceRequest req,String imageName) {
		AttendanceEntity attendanceEntity = new AttendanceEntity();
		attendanceEntity.setAppVersionCode(req.getAppVersionCode());
		attendanceEntity.setCampaignId(req.getCampaignId());
		attendanceEntity.setHaveAarogyaSetu(req.getHaveAarogyaSetu());
		attendanceEntity.setUserId(req.getUserId());
		attendanceEntity.setActivity(req.getActivity());
		attendanceEntity.setIn_status("true");
		attendanceEntity.setCurrentStatus("Active");
		attendanceEntity.setIsOffline("false");
		attendanceEntity.setMarkinOffline(req.getMarkinOffline());
		attendanceEntity.setHealthStatus(req.getHealthStatus());
		attendanceEntity.setInLatitude(req.getInLatitude());
		attendanceEntity.setInLocation(req.getInLocation());
		attendanceEntity.setInLongitude(req.getInLongitude());
		attendanceEntity.setInOrOut(req.getInOrOut());
		attendanceEntity.setDateCreated(LocalDateTime.now());
		attendanceEntity.setHealthStatus(req.getHealthStatus());
		attendanceEntity.setMaskValidation(req.getMaskValidation());
		attendanceEntity.setInDistance(req.getInDistance());
		// Mark in image code
		// attendanceEntity.setInImage(req.getInImage());
		attendanceEntity.setInImage("/webapp/image/MarkIn/Photos"+imageName);
		attendanceEntity.setIn_status(req.getIn_status());
		attendanceEntity.setIn_date(LocalDate.now());
		attendanceEntity.setInTime(LocalTime.now());
		attendanceEntity.setStatus(req.getStatus());

		if (req.getStoreId() != 0) {
			attendanceEntity.setStoreId(req.getStoreId());
			attendanceEntity.setPjpId(req.getPjpId());
		} else {
			attendanceEntity.setStoreId(-1);
			attendanceEntity.setPjpId(-1);
		}

		return attendanceEntity;
	}

	private AttendanceEntity forMarkOut(AttendanceRequest req,String imageName) {
		AttendanceEntity attendanceEntity = new AttendanceEntity();
		attendanceEntity.setAppVersionCode(req.getAppVersionCode());
		attendanceEntity.setCampaignId(req.getCampaignId());
		attendanceEntity.setHaveAarogyaSetu(req.getHaveAarogyaSetu());
		attendanceEntity.setUserId(req.getUserId());
		attendanceEntity.setStoreId(req.getStoreId());
		attendanceEntity.setActivity(req.getActivity());
		attendanceEntity.setOutStatus("true");
		attendanceEntity.setCurrentStatus("offline");
		attendanceEntity.setIsOffline("true");
		attendanceEntity.setMarkoutOffline(req.getMarkoutOffline());
		attendanceEntity.setOutLatitude(req.getOutLatitude());
		attendanceEntity.setOutLongitude(req.getOutLongitude());
		attendanceEntity.setOutLocation(req.getOutLocation());
		attendanceEntity.setDateModified(LocalDateTime.now());
		attendanceEntity.setHealthStatus(req.getHealthStatus());
		attendanceEntity.setMaskValidation(req.getMaskValidation());
		attendanceEntity.setStatus(req.getStatus());
		if (req.getStoreId() != 0) {
			attendanceEntity.setStoreId(req.getStoreId());
			attendanceEntity.setPjpId(req.getPjpId());
		} else {
			attendanceEntity.setStoreId(-1);
			attendanceEntity.setPjpId(-1);
		}
		// Mark out image code
		// attendanceEntity.setOutImage(req.getOutImage());
		attendanceEntity.setOutImage("/webapp/image/MarkOut/Photos"+imageName);
		attendanceEntity.setInOrOut(req.getInOrOut());
		attendanceEntity.setOutDistance(req.getOutDistance());
		attendanceEntity.setOutDate(LocalDate.now());
		attendanceEntity.setOutTime(LocalTime.now());
		attendanceEntity.setPjpId(req.getPjpId());
		return attendanceEntity;
	}

	public AttendanceEntity getAttendanceEntity(AttendanceRequest req,String imageName) {
		if (status.equals(req.getStatus())) {
			return this.forMarkOut(req, imageName);
		} else {

			return this.forMarkIn(req,imageName);
		}
	}

}
