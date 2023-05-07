package com.isfa.leave.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isfa.leave.entities.LeaveRequestInfo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class LeaveRequestInfoRequest extends BaseRequest{

	public static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfoRequest.class);
	
	private Long leaveRequestId;
	
	private Long companyId;
	
	private Long userId;
	
	private Long leaveId;
	
	private Long dayId;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	private Double numberOfDays;
	
	private String reason;
	
	private String leaveStatus;
	
	private String leaveRemark;
	
	private LocalDateTime createdDate;
	
	private String approvedBy;
	
	private LocalDate approvedDate;

    private LocalDateTime modifiedDate;

    private String modifiedBy;
	
    
    
    public LeaveRequestInfo convertInto() {
		logger.info("LeaveRequestInfoRequest method converting LeaveRequestInfoRequest obj to LeaveRequestInfo obj executing");
		
		LeaveRequestInfo info = new LeaveRequestInfo();
		
		info.setLeaveRequestId(this.leaveRequestId);
		info.setCompanyId(this.companyId);
		info.setUserId(this.userId);
		info.setLeaveId(this.leaveId);
		info.setDayId(this.dayId);
		info.setDateFrom(this.dateFrom);
		info.setDateTo(this.dateTo);
		info.setNumberOfDays(this.numberOfDays);
		info.setReason(this.reason);
		info.setLeaveStatus(this.leaveStatus);
		info.setLeaveRemark(this.leaveRemark);
		info.setCreatedDate(this.createdDate);
		info.setApprovedBy(this.approvedBy);
		info.setApprovedDate(this.approvedDate);
		info.setModifiedDate(this.modifiedDate);
		info.setModifiedBy(this.modifiedBy);
		
		logger.info("LeaveRequestInfoRequest method converting LeaveRequestInfoRequest obj to LeaveRequestInfo obj completed");
		return info;
    }
    
    LeaveRequestInfoRequest(){
    	logger.info("LeaveRequestInfoRequest no-args constructor executing");
    }

	public LeaveRequestInfoRequest(Long leaveRequestId, Long companyId, Long userId, Long leaveId, Long dayId,
			LocalDate dateFrom, LocalDate dateTo, Double numberOfDays, String reason, String leaveStatus,
			String leaveRemark, LocalDateTime createdDate, String approvedBy, LocalDate approvedDate,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("LeaveRequestInfoRequest param constructor executing");
		this.leaveRequestId = leaveRequestId;
		this.companyId = companyId;
		this.userId = userId;
		this.leaveId = leaveId;
		this.dayId = dayId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.numberOfDays = numberOfDays;
		this.reason = reason;
		this.leaveStatus = leaveStatus;
		this.leaveRemark = leaveRemark;
		this.createdDate = createdDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("LeaveRequestInfoRequest param constructor completed");
	}
    
    
    
}
