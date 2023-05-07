package com.isfa.leave.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LeaveTypeBalance {

	String leaveTypeName;
	Double leaveTypeBalance;
	String leaveTypeColor;
	String leaveTypeIcon;

}
