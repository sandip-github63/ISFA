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
@Table(name = "color")
public class Color {
	
	public static final Logger logger = LoggerFactory.getLogger(Color.class);
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    @Column(name = "color_code")
    private String type;

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


   
	//no args constructor
	public Color() {
		super();
		logger.info("Color no-args constructor executed");
	}

	
	//parameterized constructor
	public Color(Long id, String type, boolean active, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("Color parameterized constructor executing");
		this.id = id;
		this.type = type;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("Color parameterized constructor completed");
	}


}
