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
@Table(name = "leave_balance_info")
public class LeaveBalanceInfo {

	public static final Logger logger = LoggerFactory.getLogger(LeaveBalanceInfo.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveBalanceId;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "leave_type_id")
	private Long leaveId;

	@Column(name = "total_allocated")
	private Long totalAllocated;

	@Column(name = "total_balance")
	private Double balance;

	@Column(name = "year_of_balance")
	private Integer yearOfBalance;

	@Column(name = "leave_cycle_from")
	private LocalDate leaveCycleFrom;

	@Column(name = "leave_cycle_to")
	private LocalDate leaveCycleTo;

	@Column(name = "created_date",updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	 //no args constructor
	public LeaveBalanceInfo() {
		super();
		logger.info("LeaveBalanceInfo no-args constructor executed");
	}

	 //parameterized constructor
	public LeaveBalanceInfo(Long leaveBalanceId, Long companyId, Long userId, Long leaveId, Long totalAllocated,
			Double balance, Integer yearOfBalance, LocalDate leaveCycleFrom, LocalDate leaveCycleTo, LocalDateTime createdDate, LocalDateTime modifiedDate,
			String modifiedBy) {
		super();
		logger.info("LeaveBalanceInfo parameterized constructor executing");
		this.leaveBalanceId = leaveBalanceId;
		this.companyId = companyId;
		this.userId = userId;
		this.leaveId = leaveId;
		this.totalAllocated = totalAllocated;
		this.balance = balance;
		this.yearOfBalance = yearOfBalance;
		this.leaveCycleFrom = leaveCycleFrom;
		this.leaveCycleTo = leaveCycleTo;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		
		logger.info("LeaveBalanceInfo parameterized constructor completed");
	}
}
