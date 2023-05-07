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
@Table(name = "icon")
public class Icon {

	public static final Logger logger = LoggerFactory.getLogger(Icon.class);
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "icon_id")
	 private Long iconId;
	
	 @Column(name = "icon_name")
	 private String iconType;
	 
	 @Column(name="active")
	 private Boolean active;
	
	 @Column(name = "created_date", updatable = false)
	 private LocalDateTime createdDate;
	
	 @Column(name = "created_by", updatable = false)
	 private String createdBy;
	
	 @Column(name = "modified_date")
	 private LocalDateTime modifiedDate;
	
	 @Column(name = "modified_by")
	 private String modifiedBy;

 
 
	public Icon() {
		super();
		logger.info("Icon no-args constructor executed");
	}



	public Icon(Long iconId, String iconType, boolean active, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("Icon parameterized constructor executing");
		this.iconId = iconId;
		this.iconType = iconType;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("Icon parameterized constructor completed");
	}



	public Long getIconId() {
		return iconId;
	}



	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}



	public String getIconType() {
		return iconType;
	}



	public void setIconType(String iconType) {
		this.iconType = iconType;
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

