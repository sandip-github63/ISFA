package com.isfa.clientadminpanel.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.clientadminpanel.leave.entities.LeaveType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class LeaveTypeResponse extends BaseResponse<LeaveTypeResponse> {
	
	public static final Logger logger = LoggerFactory.getLogger(LeaveTypeResponse.class);
	
	private Long leaveId;
	private String leaveType;
	private String leaveDesc;
	private Boolean specialLeave;
	private Boolean active;
	private String color;
    private String icon;
    private Long yearlyBalance;
    

	public static LeaveTypeResponse convertToLeaveTypeResponse(LeaveType leaveType) {
		logger.info("LeaveTypeResponse method converting LeaveType obj to LeaveTypeResponse obj executing");
		LeaveTypeResponse response = new LeaveTypeResponse();
		response.setLeaveId(leaveType.getLeaveId());
		response.setLeaveType(leaveType.getLeaveType());
		response.setLeaveDesc(leaveType.getLeaveDesc());
		response.setSpecialLeave(leaveType.getSpecialLeave());
		response.setActive(leaveType.getActive());
		response.setColor(leaveType.getColor());
		response.setIcon(leaveType.getIcon());
		response.setYearlyBalance(leaveType.getYearlyBalance());
		logger.info("LeaveTypeResponse method converting LeaveType obj to LeaveTypeResponse obj completed");
		return response;
		
	}

	
	public static BaseResponse<LeaveTypeResponse> convert(LeaveType leaveType) {
		logger.info("LeaveTypeResponse method converting LeaveType obj to LeaveTypeResponse obj executing");
		LeaveTypeResponse response = new LeaveTypeResponse();
		BaseResponse<LeaveTypeResponse> bResp = new BaseResponse<>();
		response.setLeaveId(leaveType.getLeaveId());
		response.setLeaveType(leaveType.getLeaveType());
		response.setLeaveDesc(leaveType.getLeaveDesc());
		response.setSpecialLeave(leaveType.getSpecialLeave());
		response.setActive(leaveType.getActive());
		response.setColor(leaveType.getColor());
		response.setIcon(leaveType.getIcon());
		response.setYearlyBalance(leaveType.getYearlyBalance());
		bResp.setData(response);
		logger.info("LeaveTypeResponse method converting LeaveType obj to LeaveTypeResponse obj completed");
		return bResp;
		
	}
	
	
	public static BaseResponse<LeaveTypeResponse> convertList(List<LeaveType> leaveType) {
		logger.info("LeaveTypeResponse method converting LeaveType List type obj to LeaveTypeResponse List type obj executing");
		List<LeaveTypeResponse> list = new ArrayList<>(leaveType.size());
		BaseResponse<LeaveTypeResponse> bResp = new BaseResponse<>();
		if(leaveType.isEmpty()) {
			logger.info("LeaveTypeResponse method converting LeaveType List type obj to LeaveTypeResponse List type obj completed");
			bResp.setDataList(list);
			return bResp;
		}
		
		for(LeaveType type :leaveType) {
		
			LeaveTypeResponse response = new LeaveTypeResponse();
			response.setLeaveId(type.getLeaveId());
			response.setLeaveType(type.getLeaveType());
			response.setLeaveDesc(type.getLeaveDesc());
			response.setSpecialLeave(type.getSpecialLeave());
			response.setActive(type.getActive());
			response.setColor(type.getColor());
			response.setIcon(type.getIcon());
			response.setYearlyBalance(type.getYearlyBalance());
			//response.setMessage("LeaveType at "+type.getLeaveId()+" successfully fetched");
			
			list.add(response);
		}
		logger.info("IconResponse method converting Icon List type obj to LeaveTypeResponse List type obj completed");
		bResp.setDataList(list);
		return bResp;
	}
	
}
