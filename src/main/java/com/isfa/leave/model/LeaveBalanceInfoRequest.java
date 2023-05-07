package com.isfa.leave.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isfa.leave.entities.LeaveBalanceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LeaveBalanceInfoRequest extends BaseRequest {

	public static final Logger logger = LoggerFactory.getLogger(LeaveBalanceInfoRequest.class);
	
	private Long leaveBalanceId;

	private Long companyId;

	private Long userId;

	private Long leaveId;

	private Long totalAllocated;

	private Double balance;

	private Integer yearOfBalance;

	private LocalDate leaveCycleFrom;

	private LocalDate leaveCycleTo;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	private String modifiedBy;

	
	public LeaveBalanceInfo convertInto() {
		logger.info("LeaveBalanceInfoRequest method converting LeaveBalanceInfoRequest obj to LeaveBalanceInfo obj executing");
		
		LeaveBalanceInfo info = new LeaveBalanceInfo();
		
		info.setLeaveBalanceId(this.leaveBalanceId);
		info.setCompanyId(this.companyId);
		info.setUserId(this.userId);
		info.setLeaveId(this.leaveId);
		info.setTotalAllocated(this.totalAllocated);
		info.setBalance(this.balance);
		info.setYearOfBalance(this.yearOfBalance);
		info.setLeaveCycleFrom(this.leaveCycleFrom);
		info.setLeaveCycleTo(this.leaveCycleTo);
		info.setCreatedDate(this.createdDate);
		info.setModifiedDate(this.modifiedDate);
		info.setModifiedBy(this.modifiedBy);
		
		logger.info("LeaveBalanceInfoRequest method converting LeaveBalanceInfoRequest obj to LeaveBalanceInfo obj completed");
		return info;
	}
	
}
