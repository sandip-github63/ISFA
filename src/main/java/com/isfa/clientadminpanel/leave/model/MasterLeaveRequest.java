package com.isfa.clientadminpanel.leave.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.leave.entities.MasterLeave;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MasterLeaveRequest extends BaseRequest {
	
	public static final Logger logger = LoggerFactory.getLogger(MasterLeaveRequest.class);
	
	private Long dayId;

    private String description;

    private Double quantity;

    private Boolean active;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;

    
    public MasterLeave toConvert() {
    	logger.info("MasterLeaveRequest method converting MasterLeaveRequest obj to MasterLeave obj executing");
    	MasterLeave leave = new MasterLeave();
    	
    	leave.setDayId(this.dayId);
    	leave.setDescription(this.description);
    	leave.setQuantity(this.quantity);
    	leave.setActive(this.active);
    	leave.setCreatedDate(this.createdDate);
    	leave.setCreatedBy(this.createdBy);
    	leave.setModifiedDate(this.modifiedDate);
    	leave.setModifiedBy(this.modifiedBy);
    	logger.info("MasterLeaveRequest method converting MasterLeaveRequest obj to MasterLeave obj completed");
    	return leave;
    }
}
