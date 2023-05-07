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
@Table(name = "leave_day_type")
public class MasterLeave {
	
	public static final Logger logger = LoggerFactory.getLogger(MasterLeave.class);
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long dayId;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "quantity")
	    private Double quantity;

	    @Column(name = "active")
	    private Boolean active;

	    @Column(name = "created_date", updatable = false)
	    private LocalDateTime createdDate;

	    @Column(name = "created_by", updatable = false)
	    private String createdBy;

	    @Column(name = "modified_date")
	    private LocalDateTime modifiedDate;

	    @Column(name = "modified_by")
	    private String modifiedBy;

	    //no-args cons
		public MasterLeave() {
			super();
			logger.info("MasterLeave no-args constructor executed");
		}

		//parameterized cons
		public MasterLeave(Long dayId, String description, Double quantity, Boolean active, LocalDateTime createdDate,
				String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
			super();
			logger.info("MasterLeave parameterized constructor executing");
			this.dayId = dayId;
			this.description = description;
			this.quantity = quantity;
			this.active = active;
			this.createdDate = createdDate;
			this.createdBy = createdBy;
			this.modifiedDate = modifiedDate;
			this.modifiedBy = modifiedBy;
			logger.info("MasterLeave parameterized constructor completed");
		}

		public Long getDayId() {
			return dayId;
		}

		public void setDayId(Long dayId) {
			this.dayId = dayId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getQuantity() {
			return quantity;
		}

		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public LocalDateTime getModifiedDate() {
			return modifiedDate;
		}

		public void setModifiedDate(LocalDateTime modifiedDate) {
			this.modifiedDate = modifiedDate;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public static Logger getLogger() {
			return logger;
		}
   
}
