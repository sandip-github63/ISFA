package com.isfa.clientadminpanel.leave.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class BaseRequest {
	
	private String task;
	
	public static final Logger logger = LoggerFactory.getLogger(BaseRequest.class);

		//no args constructor
	public BaseRequest() {
		super();
		logger.info("BaseRequest constructor executing..");
	}
	
	
}
