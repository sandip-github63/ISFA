package com.isfa.clientadminpanel.leave.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {

	String message;
	String status;
	T data;
	List<T> dataList;
	
	
}
