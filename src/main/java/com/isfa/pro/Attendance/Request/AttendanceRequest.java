package com.isfa.pro.Attendance.Request;

import java.util.Date;

import com.isfa.pro.Attendance.entity.AttendanceEntity;

import lombok.Data;

@Data
public class AttendanceRequest extends BaseRequest {
	private long attendanceid;
	private String activity;
	private int campaignId;
	private String currentStatus;
	private Date dateCreated;
	private Date dateModified;
	private int haveAarogyaSetu;
	private String healthStatus;
	private String inDistance;     //MarkIn Data
	private String inImage;
	private String inLatitude;
	private String inLocation;
	private String inLongitude;
	private String in_status;
	private String inTime;
	private Date in_date;
	private String isOffline;
	private String markinOffline;
	private String markoutOffline;
	private String maskValidation;
	private String modifiedBy;
	private String inOrOut;
	private String outDistance;          //MarkOut
	private String outImage;
	private String outLatitude;
	private String outLocation;
	private String outLongitude;
	private String outStatus;
	private String outTime;
	private Date   outDate;
	private int pjpId;
	private int storeId;
	private String isOnStore;
	private String userId;
	private String status;

}