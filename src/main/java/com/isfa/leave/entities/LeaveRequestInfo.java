package com.isfa.leave.entities;

import java.time.LocalDate;
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
@Table(name="leave_request")
public class LeaveRequestInfo {

	public static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfo.class);
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_request_id")
    private Long leaveRequestId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "user_id")
    private Long userId;
	
    @Column(name = "leave_type_id")
    private Long leaveId;

    @Column(name = "day_id")
    private Long dayId;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "number_of_days")
    private Double numberOfDays;

    @Column(name = "reason")
    private String reason;

    @Column(name = "leave_status")
    private String leaveStatus;

    @Column(name = "leave_remark")
    private String leaveRemark;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

	 
	 //no args constructor
	 public LeaveRequestInfo() {
			super();
			logger.info("LeaveRequestInfo no-args constructor executed");
	 }

	 //parameterized constructor
	 public LeaveRequestInfo(Long leaveRequestId, Long companyId, Long userId, Long leaveId, Long dayId, LocalDate dateFrom,
				LocalDate dateTo, Double numberOfDays, String reason, String leaveStatus, String leaveRemark,
				LocalDateTime createdDate, String approvedBy, LocalDate approvedDate, LocalDateTime modifiedDate,
				String modifiedBy) {
			super();
			logger.info("LeaveRequestInfo parameterized constructor executing");
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
			
			logger.info("LeaveRequestInfo parameterized constructor completed");
		}


	
}

