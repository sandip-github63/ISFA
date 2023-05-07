package com.isfa.pro.Attendance.Request;

import lombok.Data;

@Data
public class BaseRequest {
	private String userToken;
	private String sessionId;
	private String plateformType;
	private String deviceId;
	private String responseToken;
	private int appVersionCode;

}
