package com.isfa.clientadminpanel.leave.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "leavetype")
public class LeaveType {
	    
	public static final Logger logger = LoggerFactory.getLogger(LeaveType.class);
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="leave_type_id")
	    private Long leaveId;
	    
	    @Column(name = "leave_type_name")
	    private String leaveType;
	    
	    @Column(name = "leave_desc")
	    private String leaveDesc;
	    
	    @Column(name = "special_leave")
	    private Boolean specialLeave;
	    
//	    @OneToOne(cascade = CascadeType.PERSIST)
//	    @JoinColumn(name = "color_id")
//	    private TmColor color;

	   @Column(name = "color_id")
	    private String color;
	    
	    
//	    @OneToOne(cascade = CascadeType.PERSIST)
//	    @JoinColumn(name = "icon_id")
//	    private Icon icon; 
	    
	   @Column(name = "icon_id")
	    private String icon; 
	    
	    @Column(name = "active")
	    private Boolean active;
	    
	    @Column(name = "created_date",updatable = false)
	    private LocalDateTime createdDate;
	    
	    @Column(name = "created_by",updatable = false)
	    private String createdBy;
	    
	    @Column(name = "modified_date")
	    private LocalDateTime modifiedDate;
	    
	    @Column(name = "modified_by")
	    private String modifiedBy;
	    
	    @Column(name="yearly_balance")
	    private Long yearlyBalance;

	    //no-args cons
		public LeaveType() {
			super();
			logger.info("LeaveType no-args constructor executed");
		}

		//parameterized cons
		public LeaveType(Long leaveId, String leaveType, String leaveDesc, Boolean specialLeave, String color,
				String icon, Boolean active, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate,
				String modifiedBy, Long yearlyBalance) {
			super();
			logger.info("LeaveType parameterized constructor executing");
			this.leaveId = leaveId;
			this.leaveType = leaveType;
			this.leaveDesc = leaveDesc;
			this.specialLeave = specialLeave;
			this.color = color;
			this.icon = icon;
			this.active = active;
			this.createdDate = createdDate;
			this.createdBy = createdBy;
			this.modifiedDate = modifiedDate;
			this.modifiedBy = modifiedBy;
			this.yearlyBalance = yearlyBalance;
			logger.info("LeaveType parameterized constructor completed");
		}


		
	
}
