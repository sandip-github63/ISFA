package com.isfa.clientadminpanel.leave.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.leave.entities.LeaveType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LeaveTypeRequest extends BaseRequest {

	public static final Logger logger = LoggerFactory.getLogger(LeaveTypeRequest.class);
	
	private Long leaveId;
	    
    private String leaveType;
    
    private String leaveDesc;
	    
	private Boolean specialLeave;
	    
    private String color;
    
    private String icon;
	    
	private Boolean active;
	    
    private LocalDateTime createdDate;
	    
    private String createdBy;
	    
    private LocalDateTime modifiedDate;
	    
    private String modifiedBy;
	
    private Long yearlyBalance;
    
    public LeaveType toConvert() {
    	logger.info("LeaveTypeRequest method converting LeaveTypeRequest obj to LeaveType obj executing");
    	LeaveType leaveType = new LeaveType();
    	
    	leaveType.setLeaveId(this.leaveId);
    	leaveType.setLeaveType(this.leaveType);
    	leaveType.setLeaveDesc(this.leaveDesc);
    	leaveType.setSpecialLeave(this.specialLeave);
    	leaveType.setColor(this.color);
    	leaveType.setIcon(this.icon);
    	leaveType.setActive(this.active);
    	leaveType.setCreatedDate(this.createdDate);
    	leaveType.setCreatedBy(this.createdBy);
    	leaveType.setModifiedDate(this.modifiedDate);
    	leaveType.setModifiedBy(this.modifiedBy);
    	leaveType.setYearlyBalance(this.yearlyBalance);
    	logger.info("LeaveTypeRequest method converting LeaveTypeRequest obj to LeaveType obj completed");
    	return leaveType;
    }
}
