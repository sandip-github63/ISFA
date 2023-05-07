package com.isfa.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.leave.entities.LeaveBalanceInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class LeaveBalanceInfoResponse extends BaseResponse<LeaveBalanceInfoResponse> {

	public static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfoResponse.class);

	private Long totalAllocated;

	private Double balance;

	private Integer yearOfBalance;

	private String leaveCycleFrom;

	private String leaveCycleTo;

	

	public static BaseResponse<LeaveBalanceInfoResponse> convert(LeaveBalanceInfo info) {
		logger.info("LeaveBalanceInfoResponse method converting LeaveBalanceInfo obj to LeaveBalanceInfoResponse obj executing");
		LeaveBalanceInfoResponse response = new LeaveBalanceInfoResponse();
		BaseResponse<LeaveBalanceInfoResponse> bResp = new BaseResponse<>();
		
		response.setTotalAllocated(info.getTotalAllocated());
		response.setBalance(info.getBalance());
		response.setYearOfBalance(info.getYearOfBalance());
		response.setLeaveCycleFrom(info.getLeaveCycleFrom().toString());
		response.setLeaveCycleTo(info.getLeaveCycleTo().toString());

		bResp.setData(response);
		logger.info("LeaveBalanceInfoResponse method converting LeaveBalanceInfo obj to LeaveBalanceInfoResponse obj completed");
		return bResp;
	}

	public static BaseResponse<LeaveBalanceInfoResponse> convertList(List<LeaveBalanceInfo> infoList) {
		logger.info("LeaveBalanceInfoResponse method converting LeaveBalanceInfo List to LeaveBalanceInfoResponse List executing");
		List<LeaveBalanceInfoResponse> list = new ArrayList<>(infoList.size());
		BaseResponse<LeaveBalanceInfoResponse> bResp = new BaseResponse<>();
		
		if(infoList.isEmpty()) {
			bResp.setDataList(list);
			return bResp;
		}
		
		for (LeaveBalanceInfo info : infoList) {

			LeaveBalanceInfoResponse response = new LeaveBalanceInfoResponse();

			response.setTotalAllocated(info.getTotalAllocated());
			response.setBalance(info.getBalance());
			response.setYearOfBalance(info.getYearOfBalance());
			response.setLeaveCycleFrom(info.getLeaveCycleFrom().toString());
			response.setLeaveCycleTo(info.getLeaveCycleTo().toString());
			//response.setMessage("LeaveBalanceInfo at "+info.getLeaveId()+" successfully fetched");
			
			list.add(response);
		}
		bResp.setDataList(list);
		logger.info("LeaveBalanceInfoResponse method converting LeaveBalanceInfo List to LeaveBalanceInfoResponse List completed");
		return bResp;
	}
}
