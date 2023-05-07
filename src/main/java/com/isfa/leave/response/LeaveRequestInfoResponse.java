package com.isfa.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.leave.entities.LeaveRequestInfo;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@Component
public class LeaveRequestInfoResponse extends BaseResponse<LeaveRequestInfoResponse>{
	
	private UserRepository userRepository;

	public static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfoResponse.class);
	
	private String userName;
	
	private Long userId;
	
	private Long dayId;
	
	private String dateFrom;
	
	private String dateTo;
	
	private Double numberOfDays;
	
	private String reason;
	
	private String leaveStatus;
	
	private String leaveRemark;

	public LeaveRequestInfoResponse() {
		
	}
	
	public LeaveRequestInfoResponse(UserRepository repo) {
		this.userRepository = repo;
	}
	
	public  BaseResponse<LeaveRequestInfoResponse> convert(LeaveRequestInfo info){
		logger.info("LeaveRequestInfoResponse method converting LeaveRequestInfo obj to LeaveRequestInfoResponse obj executing");
		LeaveRequestInfoResponse response = new LeaveRequestInfoResponse();
		BaseResponse<LeaveRequestInfoResponse> bResp = new BaseResponse<>();
		
		response.setDateFrom(info.getDateFrom().toString());
		response.setDateTo(info.getDateTo().toString());
		response.setNumberOfDays(info.getNumberOfDays());
		response.setReason(info.getReason());
		response.setUserId(info.getUserId());
		response.setUserName(getUserName(info.getUserId()));
		response.setLeaveStatus(info.getLeaveStatus());
		response.setLeaveRemark(info.getLeaveRemark());
		response.setDayId(info.getDayId());
		
		bResp.setData(response);
		logger.info("LeaveRequestInfoResponse method converting LeaveRequestInfo obj to LeaveRequestInfoResponse obj completed");
		return bResp;
	}
	
	
	public  BaseResponse<LeaveRequestInfoResponse> convertList(List<LeaveRequestInfo> list){
		logger.info("LeaveRequestInfoResponse method converting LeaveRequestInfo List to LeaveRequestInfoResponse List executing");
		List<LeaveRequestInfoResponse> responseList = new ArrayList<>();
		BaseResponse<LeaveRequestInfoResponse> bResp = new BaseResponse<>();
		if(list.isEmpty()) {
			bResp.setDataList(responseList);
			return bResp;
		}
		
		for (LeaveRequestInfo leaveRequestInfo : list) {
		    
			LeaveRequestInfoResponse response = new LeaveRequestInfoResponse();
		    
			response.setDateFrom(leaveRequestInfo.getDateFrom().toString());
		    response.setDateTo(leaveRequestInfo.getDateTo().toString());
		    response.setNumberOfDays(leaveRequestInfo.getNumberOfDays());
		    response.setReason(leaveRequestInfo.getReason());
		    response.setLeaveStatus(leaveRequestInfo.getLeaveStatus());
		    response.setUserName(getUserName(leaveRequestInfo.getUserId()));
		    response.setUserId(leaveRequestInfo.getUserId());
		    response.setLeaveRemark(leaveRequestInfo.getLeaveRemark());
		    response.setDayId(leaveRequestInfo.getDayId());
		    //response.setMessage("LeaveRequest Info at "+leaveRequestInfo.getLeaveId()+" fetched");
		    responseList.add(response);
		}

		bResp.setDataList(responseList);
		logger.info("LeaveRequestInfoResponse method converting LeaveRequestInfo List to LeaveRequestInfoResponse List completed");
		return bResp;
	}
	
	public String getUserName(Long userId) {
	    logger.info("LeaveRequestInfoResponse getUserName() executing");
	    User user = userRepository.findById(userId).orElse(null);
	    if (user != null) {
	        logger.info("LeaveRequestInfoResponse getUserName() completed");
	        System.out.println("User found: " + user);
	        return user.getUsername();
	    } else {
	        logger.info("LeaveRequestInfoResponse getUserName() completed");
	        System.out.println("User not found");
	        return null;
	    }
	}

	
}
