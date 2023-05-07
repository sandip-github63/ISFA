package com.isfa.leave.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteeRequestedLeave {

	Long leaveRequestId;
	String userName;
	String leaveStatus;
	String leaveType;
	String reason;
	String fromDate;
	String toDate;
	
}



