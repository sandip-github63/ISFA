package com.isfa.clientadminpanel.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.clientadminpanel.leave.entities.MasterLeave;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class MasterLeaveResponse extends BaseResponse<MasterLeaveResponse> {
	
	public static final Logger logger = LoggerFactory.getLogger(MasterLeaveResponse.class);
	
	private Long dayId;
	private String description;
	private Double quantity;
	private Boolean active;
	

	public static BaseResponse<MasterLeaveResponse> convert(MasterLeave leave){
		logger.info("MasterLeaveResponse method converting MasterLeave obj to MasterLeaveResponse obj executing");
		MasterLeaveResponse response = new MasterLeaveResponse();
		BaseResponse<MasterLeaveResponse> bResp = new BaseResponse<>();
		response.setDayId(leave.getDayId());
		response.setDescription(leave.getDescription());
		response.setQuantity(leave.getQuantity());
		response.setActive(leave.getActive());
		bResp.setData(response);
		logger.info("MasterLeaveResponse method converting MasterLeave obj to MasterLeaveResponse obj completed");
		return bResp;
	}

	
	public static BaseResponse<MasterLeaveResponse> convertList(List<MasterLeave> leaveList){
		logger.info("MasterLeaveResponse method converting MasterLeave List type obj to MasterLeaveResponse List type obj executing");
		List<MasterLeaveResponse> list = new ArrayList<>(leaveList.size());
		BaseResponse<MasterLeaveResponse> bResp = new BaseResponse<>();
		if(leaveList.isEmpty()) {
			logger.info("MasterLeaveResponse method converting MasterLeave List type obj to MasterLeaveResponse List type obj completed");
			bResp.setDataList(list);
			return bResp;
		}
		
		for(MasterLeave leave:leaveList) {
			
			MasterLeaveResponse response = new MasterLeaveResponse();
			
			response.setDayId(leave.getDayId());
			response.setDescription(leave.getDescription());
			response.setQuantity(leave.getQuantity());
			response.setActive(leave.getActive());
			//response.setMessage("MasterLeave at "+leave.getDayId()+" successfully fetched");
			
			list.add(response);
		}
		logger.info("MasterLeaveResponse method converting MasterLeave List type obj to MasterLeaveResponse List type obj completed");
		bResp.setDataList(list);
		return bResp;
	}
	
	
	
}
