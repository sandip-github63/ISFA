package com.isfa.pro.AttendanceStartDuty.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "tt_attendanceinfo_temp")
public class AttendanceStartDutyEntity {
	@Id
	@Column(name="attendance_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendanceid;
	@Column(name = "activity")
	private String activity;
	@Column(name = "app_version_code")
	private int appVersionCode;
	@Column(name = "campaign_id")
	private int campaignId;
	@Column(name = "current_status")
	private String currentStatus;
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	@Column(name = "date_modified")
	private LocalDateTime dateModified;
	@Column(name = "haveaarogyasetu")
	private int haveAarogyaSetu;
	@Column(name = "health_status")
	private String healthStatus;
	@Column(name = "in_distance")
	private String inDistance;
	@Column(name = "in_image")
	private String inImage;
	@Column(name = "in_latitude")
	private String inLatitude;
	@Column(name = "inlocation")
	private String inLocation;
	@Column(name = "in_longitude")
	private String inLongitude;
	@Column(name = "in_status")
	private String in_status;
	@Column(name = "in_time")
	private LocalTime inTime;
	@Column(name = "is_offline")
	private String isOffline;
	@Column(name = "markin_offline")
	private String markinOffline;
	@Column(name = "in_date")
	private LocalDate in_date;
	@Column(name = "markout_offline")
	private String markoutOffline;
	@Column(name = "mask_validation")
	private String maskValidation;
	private String inOrOut;
	@Column(name = "date_modified_by")
	private String modifiedBy;
	@Column(name = "out_distance")
	private String outDistance;
	@Column(name = "out_image")
	private String outImage;
	@Column(name = "out_latitude")
	private String outLatitude;
	@Column(name = "outlocation")
	private String outLocation;
	@Column(name = "out_longtitude")
	private String outLongitude;
	@Column(name = "out_status")
	private String outStatus;
	@Column(name = "out_time")
	private LocalTime outTime;
	@Column(name = "out_date")
	private LocalDate outDate;
	@Column(name = "pjp_id")
	private int pjpId;
	@Column(name = "store_id")
	private int storeId;
	@Column(name = "user_id")
	private String userId;
	@Transient
	private String status;

}
