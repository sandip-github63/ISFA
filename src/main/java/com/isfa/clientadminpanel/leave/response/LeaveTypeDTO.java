package com.isfa.clientadminpanel.leave.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveTypeDTO<T> {
	
	private String message;
	private Integer status;
	private T data;
//    private BaseResponse<LeaveTypeResponse> leaveTypeResponses;
    private byte[] icon;

}
