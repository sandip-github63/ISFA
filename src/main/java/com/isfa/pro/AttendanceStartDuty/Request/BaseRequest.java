package com.isfa.pro.AttendanceStartDuty.Request;

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
